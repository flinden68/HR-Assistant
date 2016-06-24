package ch.belsoft.hrassistant.job.dao;

import java.io.Serializable;
import java.util.List;

import ch.belsoft.hrassistant.dao.ICrudDAO;
import ch.belsoft.hrassistant.job.model.Job;
import ch.belsoft.hrassistant.service.CloudantService;

public class JobDAO implements ICrudDAO<Job, String>, Serializable {
    
    private static final long serialVersionUID = 1L;
    private final CloudantService cloudantService = null;
    
    public void create(Job t) {
        cloudantService.saveDocument(t);
    }
    
    public void delete(Job t) {
        cloudantService.removeDocument(t);
        
    }
    
    public Job read(String id) {
        return (Job) cloudantService.findDocumentByID(Job.class, id);
    }
    
    @SuppressWarnings("unchecked")
    public List<Job> read() {
        return (List<Job>) cloudantService.findAllDocuments(Job.class);
    }
    
    public void update(Job t) {
        cloudantService.updateDocument(t);
    }
    
    public void connectToService() {
        if(!cloudantService.isConnected()){
            cloudantService.connect();
        }
    }
    
}
