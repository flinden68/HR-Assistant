package ch.belsoft.hrassistant.model;

import java.io.Serializable;
import java.util.Date;

import ch.belsoft.hrassistant.dao.IDataItem;

public abstract class DataItem implements Serializable, IDataItem {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    protected String _rev = null;
    protected String _id = null;
    protected Date created = new Date();
    protected Date modified = new Date();
    
    public enum DataType {
        CONFIG, JOB, JOBAPPLICATION, COMPANY
    }
    
    public String getRev() {
        return _rev;
    }
    
    public void setRev(String rev) {
        this._rev = rev;
    }
    
    public String getId() {
        return _id;
    }
    
    public void setId(String id) {
        this._id = id;
    }
    
    public long getCreatedMilliseconds() {
        return created.getTime();
    }
    
    public Date getCreated() {
        return created;
    }
    
    public void setCreated(Date created) {
        this.created = created;
    }
    
    public void setCreatedMilliseconds(long created) {
        this.created = new Date(created);
    }
    
    public long getModifiedMilliseconds() {
        return modified.getTime();
    }
    
    public Date getModified() {
        return modified;
    }
    
    public void setModifiedMilliseconds(long modified) {
        this.modified = new Date(modified);
    }
    
    public void setModified(Date modified) {
        this.modified = modified;
    }
    
    /*
     * public String get_rev() { return _rev; }
     * 
     * public void set_rev(String _rev) { this._rev = _rev; }
     * 
     * public String get_id() { return _id; }
     * 
     * public void set_id(String _id) { this._id = _id; }
     */
}
