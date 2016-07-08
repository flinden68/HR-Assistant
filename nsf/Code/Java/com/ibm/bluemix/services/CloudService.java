package com.ibm.bluemix.services;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import nl.elstarit.cloudant.connector.CloudantConnector;
import nl.elstarit.cloudant.log.CloudantLogger;
import nl.elstarit.cloudant.model.ConnectorIndex;
import nl.elstarit.cloudant.model.ConnectorResponse;
import ch.belsoft.tools.XPagesUtil;

import com.ibm.xsp.bluemix.util.BluemixContextUtil;
import ch.belsoft.tools.RestUtil;

public abstract class CloudService {

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
