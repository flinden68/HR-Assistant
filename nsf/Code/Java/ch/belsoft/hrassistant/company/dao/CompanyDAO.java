package ch.belsoft.hrassistant.company.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ch.belsoft.hrassistant.dao.BaseDAO;
import ch.belsoft.hrassistant.dao.ICrudDAO;
import ch.belsoft.hrassistant.job.model.Company;
import ch.belsoft.tools.Logging;

public class CompanyDAO extends BaseDAO implements ICrudDAO<Company, String>,
Serializable {
    
    private static final long serialVersionUID = 1L;
    private static final String DESIGN_DOC = "company";
    private static final String VIEW_NAME = "companies";
    private static final String DESIGN_DOC_KEYS = "companykey";
    private static final String VIEW_NAME_KEYS = "companieskeys";
    private static final String SEARCH_PATTERN = "name:{QUERY} OR street:{QUERY} OR streetnumber:{QUERY} OR zip:{QUERY} OR city:{QUERY} OR country:{QUERY}";
    private static final String SEARCH_INDEX = "company/ftsearchCompanies";
    private static final int VIEW_LIMIT = 1000;
    private static final int SEARCH_COUNT = 100;
    
    public void create(Company t) {
        connectToService();
        super.handleResponse(cloudantService.saveDocument(t), t);
    }
    
    public void delete(Company t) {
        connectToService();
        cloudantService.removeDocument(t);
        
    }
    
    public Company read(String id) {
        connectToService();
        return (Company) cloudantService.findDocumentByID(Company.class, id);
    }
    
    @SuppressWarnings("unchecked")
    public List<Company> read() {
        connectToService();
        return (List<Company>) cloudantService.findAllDocumentFromView(
                Company.class, DESIGN_DOC, VIEW_NAME, "STRING", VIEW_LIMIT);
    }
    
    public void update(Company t) {
        connectToService();
        super.updateModifiedDate(t);
        super.handleResponse(cloudantService.updateDocument(t), t);
    }
    
    @SuppressWarnings("unchecked")
    public List<Company> readWithKeys(String startKey, String endKey) {
        connectToService();
        return (List<Company>) cloudantService.findAllDocumentFromViewKeys(
                Company.class, DESIGN_DOC_KEYS, VIEW_NAME_KEYS, "STRING",
                VIEW_LIMIT, startKey, endKey);
    }
    
    public List<Company> search(String query) {
        List<Company> result = new ArrayList<Company>();
        try {
            connectToService();
            String queryFinal = SEARCH_PATTERN.replace(SEARCH_QUERYREPLACE,
                    query);
            result = (List<Company>) cloudantService.search(SEARCH_INDEX,
                    Company.class, SEARCH_COUNT, queryFinal);
            
        } catch (Exception e) {
            Logging.logError(e);
        }
        
        return result;
    }
    
}
