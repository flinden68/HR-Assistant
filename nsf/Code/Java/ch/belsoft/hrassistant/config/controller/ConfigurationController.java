package ch.belsoft.hrassistant.config.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Vector;

import ch.belsoft.hrassistant.config.dao.ConfigurationDAO;
import ch.belsoft.hrassistant.config.model.ConfigType;
import ch.belsoft.hrassistant.config.model.ConfigDefault;
import ch.belsoft.hrassistant.config.model.IConfiguration;
import ch.belsoft.hrassistant.controller.ApplicationController;
import ch.belsoft.hrassistant.controller.ControllerBase;
import ch.belsoft.hrassistant.controller.IGuiController;
import ch.belsoft.tools.Util;
import ch.belsoft.tools.XPagesUtil;

public class ConfigurationController extends ControllerBase implements
		IGuiController<IConfiguration>, Serializable {

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	private static final String PAGETITLE_NEW = "New Configuration";
	private static final String PAGETITLE_EXISTING = "Configuration {TYPE}: {KEY}";
	private static final String PAGETITLE_REPLACE_TYPE = "{TYPE}";
	private static final String PAGETITLE_REPLACE_KEY = "{KEY}";
	private static final String TITLE_CONFIGURATIONLIST_ALL = "All configuration items";
	private static final String TITLE_CONFIGURATIONLIST_TYPE = "Other configuration items of type {TYPE}";

	private ConfigDefault config = new ConfigDefault();
	private ConfigurationDAO configurationDAO = new ConfigurationDAO();

	public ConfigurationController() {

	}

	public String getConfigurationListTitle() {
		String result = "";
		try {
			if (this.config.getType() == null
					|| this.config.getType().equals("")) {
				result = TITLE_CONFIGURATIONLIST_ALL
						+ this.config.getTypeString();
			} else {
				result = TITLE_CONFIGURATIONLIST_TYPE.replace(
						PAGETITLE_REPLACE_TYPE, this.config.getType()
								.toString());
			}
		} catch (Exception e) {
			Util.logError(e);
		}
		return result;
	}

	public String[] getConfigTypeSelection() {
		return ConfigType.types();
	}

	public ConfigDefault getDataContext() {
		try {
			String id = this.getId();
			if (!id.equals("")) {
				config = (ConfigDefault) configurationDAO.read(id);
			} else {
				newDataItem = true;

			}
		} catch (Exception e) {
			Util.logError(e);
		}
		return config;
	}

	public ApplicationController getApplicationController() {
		return applicationController;
	}

	public void setApplicationController(
			ApplicationController applicationController) {
		this.applicationController = applicationController;
	}

	public String getPageTitle() {
		String pageTitle = "";
		try {
			if (this.newDataItem) {
				pageTitle = PAGETITLE_NEW;
			} else {
				pageTitle = PAGETITLE_EXISTING.replace(PAGETITLE_REPLACE_TYPE,
						this.config.getType().toString());
				pageTitle = pageTitle.replace(PAGETITLE_REPLACE_KEY,
						this.config.getKey());
			}
		} catch (Exception e) {
			Util.logError(e);
		}
		return pageTitle;
	}

}
