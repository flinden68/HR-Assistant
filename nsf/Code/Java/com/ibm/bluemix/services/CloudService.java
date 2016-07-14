package com.ibm.bluemix.services;

import ch.belsoft.hrassistant.service.CloudantService;

import com.ibm.xsp.bluemix.util.BluemixContextUtil;

public abstract class CloudService implements ICloudService {

	protected abstract String getServiceName();

	protected String password;
	protected String username;

	protected BluemixContextUtil bluemixUtil;

	public CloudService() {

	}

	public void connect() {
		if (bluemixUtil == null) {
			bluemixUtil = new BluemixContextUtil(getServiceName(), username,
					password, "");
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
