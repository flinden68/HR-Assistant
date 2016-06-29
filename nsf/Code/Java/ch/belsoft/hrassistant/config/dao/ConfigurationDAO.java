package ch.belsoft.hrassistant.config.dao;

import java.io.Serializable;
import java.util.List;

import nl.elstarit.cloudant.model.ConnectorResponse;
import ch.belsoft.hrassistant.config.model.ConfigDefault;
import ch.belsoft.hrassistant.dao.ICrudDAO;
import ch.belsoft.hrassistant.service.CloudantService;

public class ConfigurationDAO implements ICrudDAO<ConfigDefault, String>,
Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private CloudantService cloudantService = null;
    private static final String DESIGN_DOC = "configuration";
    private static final String VIEW_NAME = "configurations";
    private static final int VIEW_LIMIT = 1000;
    
    public ConnectorResponse update(ConfigDefault config) {
        connectToService();
        return cloudantService.updateDocument(config);
    }
    
    public ConnectorResponse create(ConfigDefault config) {
        connectToService();
        return cloudantService.saveDocument(config);
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
        return (List<ConfigDefault>) cloudantService
        .findAllDocumentFromView(ConfigDefault.class, DESIGN_DOC, VIEW_NAME, "STRING", VIEW_LIMIT);
    }
    
    public void connectToService() {
        if (!cloudantService.isConnected()) {
            cloudantService.connect();
        }
    }
    
    public CloudantService getCloudantService() {
        return cloudantService;
    }
    
    public void setCloudantService(CloudantService cloudantService) {
        this.cloudantService = cloudantService;
    }
    
}
