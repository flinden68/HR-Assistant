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
    protected String attachmentId = "";
    protected Date created = new Date();
    protected Date modified = new Date();
    
    public enum DataType {
        CONFIG, JOB, JOBAPPLICATION, COMPANY, ATTACHMENT, UPLOAD
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
    
    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }
    
    public String getAttachmentId() {
        return attachmentId;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((_id == null) ? 0 : _id.hashCode());
        result = prime * result + ((_rev == null) ? 0 : _rev.hashCode());
        result = prime * result + ((created == null) ? 0 : created.hashCode());
        result = prime * result
        + ((modified == null) ? 0 : modified.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        DataItem other = (DataItem) obj;
        if (_id == null) {
            if (other._id != null) {
                return false;
            }
        } else if (!_id.equals(other._id)) {
            return false;
        }
        if (_rev == null) {
            if (other._rev != null) {
                return false;
            }
        } else if (!_rev.equals(other._rev)) {
            return false;
        }
        if (created == null) {
            if (other.created != null) {
                return false;
            }
        } else if (!created.equals(other.created)) {
            return false;
        }
        if (modified == null) {
            if (other.modified != null) {
                return false;
            }
        } else if (!modified.equals(other.modified)) {
            return false;
        }
        return true;
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
