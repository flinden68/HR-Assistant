package ch.belsoft.hrassistant.dao;

import java.io.Serializable;
import java.util.Date;

import nl.elstarit.cloudant.model.ConnectorResponse;
import ch.belsoft.hrassistant.controller.ApplicationController;
import ch.belsoft.hrassistant.service.CloudantService;

public abstract class BaseDAO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    protected CloudantService cloudantService = null;
    protected static final String SEARCH_QUERYREPLACE = "{QUERY}";
    
    protected void handleResponse(ConnectorResponse resp, IDataItem dataItem) {
        if(!"".equals(resp.getError())){
            dataItem.setId(resp.getId());
            dataItem.setRev(resp.getRev());
        }
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
    
    public void updateModifiedBy(IDataItem dataItem){
        String userName = ApplicationController.get().getUser().getUserName();
        dataItem.setModifiedBy(userName);
    }
    
    public void setCreatedBy(IDataItem dataItem){
        String userName = ApplicationController.get().getUser().getUserName();
        dataItem.setCreatedBy(userName);
    }
    
}
