package ch.belsoft.hrassistant.job.dao;

import java.io.Serializable;
import java.util.List;

import ch.belsoft.hrassistant.dao.ICrudDAO;
import ch.belsoft.hrassistant.job.model.Job;
import ch.belsoft.hrassistant.service.CloudantService;

public class JobDAO implements ICrudDAO<Job, String>, Serializable {
    
    private static final long serialVersionUID = 1L;
    private static final String DESIGN_DOC = "job";
    private static final String VIEW_NAME = "jobs";
    private CloudantService cloudantService = null;
    
    public void create(Job t) {
        connectToService();
        cloudantService.saveDocument(t);
    }
    
    public void delete(Job t) {
        connectToService();
        cloudantService.removeDocument(t);
        
    }
    
    public Job read(String id) {
        connectToService();
        return (Job) cloudantService.findDocumentByID(Job.class, id);
    }
    
    @SuppressWarnings("unchecked")
    public List<Job> read() {
        connectToService();
        return (List<Job>) cloudantService.findAllDocuments(Job.class);
    }
    
    public void update(Job t) {
        connectToService();
        cloudantService.updateDocument(t);
    }
    
    public void connectToService() {
        if(!cloudantService.isConnected()){
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
