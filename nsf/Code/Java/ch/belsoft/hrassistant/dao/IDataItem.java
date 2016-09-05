package ch.belsoft.hrassistant.dao;

import java.util.Date;

import ch.belsoft.hrassistant.model.DataItem;

public interface IDataItem {
    
	public String getName();
	
    public String getRev();
    
    public void setRev(String rev);
    
    public String getId();
    
    public void setId(String id);
    
    public DataItem.DataType getDataType();
    
    public Date getCreated();
    
    public void setCreated(Date created);
    
    public Date getModified();
    
    public void setModified(Date modified);
    
    public void setAttachmentId(String attachmentId);
    
    public String getAttachmentId();
    
    public void setCreatedBy(String userName);
    
    public String getCreatedBy();
    
    public void setModifiedBy(String userName);
    
    public String getModifiedBy();
    
}
