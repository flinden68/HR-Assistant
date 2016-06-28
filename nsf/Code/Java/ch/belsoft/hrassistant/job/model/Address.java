package ch.belsoft.hrassistant.job.model;

import java.io.Serializable;
import java.util.Date;

import ch.belsoft.hrassistant.config.model.ConfigDefault;
import ch.belsoft.hrassistant.config.model.ConfigType;
import ch.belsoft.hrassistant.model.DataItem;

public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String street = "";
	private String streetNumber = "";
	private String zip = "";
	private String country = null;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
