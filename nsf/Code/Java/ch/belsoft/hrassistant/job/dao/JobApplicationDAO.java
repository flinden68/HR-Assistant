package ch.belsoft.hrassistant.job.dao;

import java.io.Serializable;
import java.util.List;

import nl.elstarit.cloudant.model.ConnectorResponse;
import ch.belsoft.hrassistant.dao.ICrudDAO;
import ch.belsoft.hrassistant.job.model.JobApplication;
import ch.belsoft.hrassistant.service.CloudantService;

public class JobApplicationDAO implements ICrudDAO<JobApplication, String>, Serializable {
    
    private static final long serialVersionUID = 1L;
    private static final String DESIGN_DOC = "jobapplication";
    private static final String VIEW_NAME = "jobapplications";
    //Limit of the returned documents
    private static final int VIEW_LIMIT = 1000;
    private CloudantService cloudantService = null;
    
    public JobApplicationDAO(){
        
    }
    
    
    public ConnectorResponse create(JobApplication t) {
        connectToService();
        return cloudantService.saveDocument(t);
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
        return (List<JobApplication>) cloudantService
        .findAllDocumentFromView(JobApplication.class, DESIGN_DOC, VIEW_NAME, "STRING", VIEW_LIMIT);
    }
    
    public ConnectorResponse update(JobApplication t) {
        connectToService();
        return cloudantService.updateDocument(t);
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
