package ch.belsoft.hrassistant.config.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ch.belsoft.hrassistant.config.model.ConfigDefault;
import ch.belsoft.hrassistant.dao.BaseDAO;
import ch.belsoft.hrassistant.dao.ICrudDAO;
import ch.belsoft.tools.Logging;

public class ConfigurationDAO extends BaseDAO implements
ICrudDAO<ConfigDefault, String>, Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final String DESIGN_DOC = "configuration";
    private static final String VIEW_NAME = "configurations";
    private static final int VIEW_LIMIT = 1000;
    private static final String SEARCH_PATTERN = "configValue:{QUERY} OR configKey:{QUERY}";
    private static final String SEARCH_INDEX = "configuration/ftsearchConfigurations";
    private static final int SEARCH_COUNT = 100;
    
    public void update(ConfigDefault config) {
        connectToService();
        super.updateModifiedDate(config);
        super.updateModifiedBy(config);
        super.handleResponse(cloudantService.updateDocument(config), config);
    }
    
    public void create(ConfigDefault config) {
        connectToService();
        super.setCreatedBy(config);
        super.handleResponse(cloudantService.saveDocument(config), config);
    }
    
    public void delete(ConfigDefault config) {
        connectToService();
        cloudantService.removeDocument(config);
    }
    
    public ConfigDefault read(String id) {
        connectToService();
        return (ConfigDefault) cloudantService.findDocumentByID(
                ConfigDefault.class, id);
    }
    
    @SuppressWarnings("unchecked")
    public List<ConfigDefault> read() {
        connectToService();
        return (List<ConfigDefault>) cloudantService.findAllDocumentFromView(
                ConfigDefault.class, DESIGN_DOC, VIEW_NAME, "STRING",
                VIEW_LIMIT);
    }
    
    public List<ConfigDefault> readWithKeys(String startKey, String endKey) {
        // TODO Auto-generated method stub
        return null;
    }
    
    public List<ConfigDefault> search(String query) {
        List<ConfigDefault> result = new ArrayList<ConfigDefault>();
        try {
            connectToService();
            String queryFinal = SEARCH_PATTERN.replace(SEARCH_QUERYREPLACE,
                    query);
            result = (List<ConfigDefault>) cloudantService.search(SEARCH_INDEX,
                    ConfigDefault.class, SEARCH_COUNT, queryFinal);
            
        } catch (Exception e) {
            Logging.logError(e);
        }
        return result;
    }
}
