package ch.belsoft.hrassistant.dao;

import ch.belsoft.hrassistant.model.DataItem;

public interface IDataItem {
    
    public String getRev();
    
    public void setRev(String rev);
    
    public String getId();
    
    public void setId(String id);
    
    public DataItem.DataType getDataType();
}
