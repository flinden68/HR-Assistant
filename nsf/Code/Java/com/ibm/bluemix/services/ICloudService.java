package com.ibm.bluemix.services;

import ch.belsoft.hrassistant.service.CloudantService;

public interface ICloudService<T> {

	public void connect();

	public String getPassword();

	public void setPassword(String password);

	public String getUsername();

	public void setUsername(String username);

}
