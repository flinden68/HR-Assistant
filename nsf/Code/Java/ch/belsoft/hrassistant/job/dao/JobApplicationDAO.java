package ch.belsoft.hrassistant.job.dao;

import java.io.Serializable;
import java.util.List;

import ch.belsoft.hrassistant.dao.ICrudDAO;
import ch.belsoft.hrassistant.job.model.JobApplication;
import ch.belsoft.hrassistant.service.CloudantService;

public class JobApplicationDAO implements ICrudDAO<JobApplication, String>, Serializable {
    
    private static final long serialVersionUID = 1L;
    private CloudantService cloudantService = null;
    
    public JobApplicationDAO(){
        
    }
    
    
    public void create(JobApplication t) {
        connectToService();
        cloudantService.saveDocument(t);
    }
    
    public void delete(JobApplication t) {
        connectToService();
        cloudantService.removeDocument(t);
    }
    
    public JobApplication read(String id) {
        connectToService();
        return (JobApplication) cloudantService.findDocumentByID(JobApplication.class, id);
    }
    
    @SuppressWarnings("unchecked")
    public List<JobApplication> read() {
        connectToService();
        return (List<JobApplication>) cloudantService.findAllDocuments(JobApplication.class);
    }
    
    public void update(JobApplication t) {
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
