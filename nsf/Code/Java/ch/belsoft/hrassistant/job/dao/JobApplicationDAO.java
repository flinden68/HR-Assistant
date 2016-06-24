package ch.belsoft.hrassistant.job.dao;

import java.io.Serializable;
import java.util.List;

import ch.belsoft.hrassistant.dao.ICrudDAO;
import ch.belsoft.hrassistant.job.model.JobApplication;
import ch.belsoft.hrassistant.service.CloudantService;

public class JobApplicationDAO implements ICrudDAO<JobApplication, String>, Serializable {
    
    private static final long serialVersionUID = 1L;
    private final CloudantService cloudantService = null;
    
    public JobApplicationDAO(){
        
    }
    
    
    public void create(JobApplication t) {
        cloudantService.saveDocument(t);
    }
    
    public void delete(JobApplication t) {
        cloudantService.removeDocument(t);
    }
    
    public JobApplication read(String id) {
        return (JobApplication) cloudantService.findDocumentByID(JobApplication.class, id);
    }
    
    @SuppressWarnings("unchecked")
    public List<JobApplication> read() {
        return (List<JobApplication>) cloudantService.findAllDocuments(JobApplication.class);
    }
    
    public void update(JobApplication t) {
        cloudantService.updateDocument(t);
    }
    
    public void connectToService() {
        if(!cloudantService.isConnected()){
            cloudantService.connect();
        }
    }
    
}
