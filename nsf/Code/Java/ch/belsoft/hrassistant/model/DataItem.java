package ch.belsoft.hrassistant.model;

import java.io.Serializable;
import java.util.Date;

import ch.belsoft.hrassistant.config.model.ConfigType;
import ch.belsoft.hrassistant.dao.IDataItem;

public abstract class DataItem implements Serializable, IDataItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String rev = "";
	protected String id = "";
	protected Date created = new Date();
	protected Date modified = new Date();

	public enum DataType {
		CONFIG, JOB, JOBAPPLICATION
	}

	public String getRev() {
		return rev;
	}

	public void setRev(String rev) {
		this.rev = rev;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

}
