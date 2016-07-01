package ch.belsoft.hrassistant.dao;

import java.io.Serializable;
import java.util.Date;

import nl.elstarit.cloudant.model.ConnectorResponse;
import ch.belsoft.hrassistant.service.CloudantService;

public abstract class BaseDAO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    protected CloudantService cloudantService = null;
    
    protected void handleResponse(ConnectorResponse resp, IDataItem dataItem) {
        dataItem.setId(resp.getId());
        dataItem.setRev(resp.getRev());
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
    
    public void updateModifiedDate(IDataItem dataItem) {
        dataItem.setModified(new Date());
    }
    
    
}
