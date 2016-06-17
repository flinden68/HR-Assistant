package ch.belsoft.hrassistant.job.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import ch.belsoft.hrassistant.config.model.ConfigType;
import ch.belsoft.hrassistant.model.DataItem;

public class Job extends DataItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name = "";
	private Company company = null;
	private Person representative = null;
 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Person getRepresentative() {
		return representative;
	}

	public void setRepresentative(Person representative) {
		this.representative = representative;
	}

}
