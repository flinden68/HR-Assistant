package ch.belsoft.hrassistant.job.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import ch.belsoft.hrassistant.config.model.ConfigType;
import ch.belsoft.hrassistant.model.DataItem;

public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName = "";
	private String lastName = "";
	private Date birthDay = null;
	private Address address = null;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

}
