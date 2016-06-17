package ch.belsoft.hrassistant.job.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import ch.belsoft.hrassistant.config.model.ConfigType;
import ch.belsoft.hrassistant.model.DataItem;

public class Company extends DataItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name = "";
	private Address address = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}
 
	public void setAddress(Address address) {
		this.address = address;
	}

}
