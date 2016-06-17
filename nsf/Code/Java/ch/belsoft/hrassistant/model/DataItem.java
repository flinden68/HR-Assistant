package ch.belsoft.hrassistant.model;

import java.io.Serializable;

import ch.belsoft.hrassistant.config.model.ConfigType;

public abstract class DataItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String rev = "";
	private String id = "";

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

}
