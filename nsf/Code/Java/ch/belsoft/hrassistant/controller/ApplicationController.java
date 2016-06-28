package ch.belsoft.hrassistant.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Vector;

import ch.belsoft.hrassistant.config.model.ConfigDefault;
import ch.belsoft.hrassistant.config.model.ConfigType;
import ch.belsoft.hrassistant.config.model.IConfiguration;
import ch.belsoft.tools.Logging;
import ch.belsoft.tools.Util;
import ch.belsoft.tools.XPagesUtil;

public class ApplicationController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String REALHOMEPAGE = "dashboard.xsp";

	private LinkedHashMap<ConfigType, List<String>> configSelections = new LinkedHashMap<ConfigType, List<String>>();
	private LinkedHashMap<ConfigType, LinkedHashMap<String, IConfiguration>> config = new LinkedHashMap<ConfigType, LinkedHashMap<String, IConfiguration>>();

	public ApplicationController() {
		// initConfiguration();
	}

	/**
	 * getting the XSP Contect and redirect
	 */
	public void redirectToRealHomePage() {
		try {
			XPagesUtil.getXSPContext().redirectToPage(REALHOMEPAGE);
		} catch (Exception e) {
			Logging.logError(e);
		}
	}

	private void initConfiguration() {
		try {

		} catch (Exception e) {
			Logging.logError(e);
		}
	}

	public IConfiguration getConfig(ConfigType configType, String sKey) {
		IConfiguration result = null;

		try {

			if (!config.containsKey(configType)) {
				initConfiguration();
				Util.logEvent("Configuration with type: " + configType
						+ " not found..");
			} else {
				LinkedHashMap<String, IConfiguration> configByKeys = config
						.get(configType);
				if (!configByKeys.containsKey(sKey)) {
					Util.logEvent("Configuration in type " + configType
							+ " with key :" + sKey + " not found");
				} else {
					result = configByKeys.get(sKey);
					/*
					 * Util.logEvent("getConfig, value:  " + sType +
					 * " with key :" + sKey + " :::" + result + ", value:" +
					 * result.getValue());
					 */
				}
			}
		} catch (Exception e) {
			Logging.logError(e);
		}

		return result;
	}

	public String getConfigValue(ConfigType configType, String sKey) {
		String result = "";
		try {
			// Util.logEvent("inside getConfigValue: " + sType + ", key: " +
			// sKey);

			if (sKey != null && !sKey.equals("")) {
				IConfiguration config = getConfig(configType, sKey);

				if (config != null) {
					result = config.getValue();
				} else {
					result = sKey;
				}
			}

		} catch (Exception e) {
			Logging.logError(e);
		}
		return result;
	}

	public List<IConfiguration> getConfigItems(String type) {
		List<IConfiguration> result = new ArrayList<IConfiguration>();

		try {

			ConfigType configType = ConfigType.valueOf(type);
			result = getConfigItems(configType);

		} catch (Exception e) {
			Logging.logError(e);
		}

		return result;
	}

	public List<IConfiguration> getConfigItems(ConfigType configType) {
		List<IConfiguration> result = new ArrayList<IConfiguration>();

		try {

			if (!config.containsKey(configType)) {
				initConfiguration();
			}

			result = new ArrayList<IConfiguration>(config.get(configType)
					.values());

		} catch (Exception e) {
			Logging.logError(e);
		}

		return result;
	}

	public List<String> getConfigSelection(String sType) {

		List<String> vResult = new Vector<String>();

		try {

			if (!configSelections.containsKey(sType)) {
				Util.logEvent("its empty, so reloooad: " + sType);
				initConfiguration();
			}

			vResult = configSelections.get(sType);

		} catch (Exception e) {
			Logging.logError(e);
		}

		return vResult;
	}

	private void addConfig(String type, String key, String value, String params) {
		try {

			ConfigType configType = ConfigType.valueOf(type);

			IConfiguration configItem = null;

			String[] arrParams = null;

			if (params.contains("|")) {
				arrParams = params.split("\\|", -1);
			}

			switch (configType) {
			// case CONNECTIONS_CREDENTIALS:
			// configItem = new ConfigCredentials(configType, key, value);
			// ((ConfigCredentials) configItem).setPassword(arrParams[0]);
			// break;
			// case BLOGPOST_TEMPLATE:
			// configItem = new ConfigBlogPostTemplate(configType, key,
			// value);
			// ((ConfigBlogPostTemplate)
			// configItem).setContent(arrParams[0]);
			// break;
			default:
				configItem = new ConfigDefault(configType, key, value);
				break;
			}

			LinkedHashMap<String, IConfiguration> mapConfig = null;
			List<String> vConfigSelection = null;

			if (config.containsKey(configItem.getType())) {
				mapConfig = config.get(configItem.getType());
			} else {
				mapConfig = new LinkedHashMap<String, IConfiguration>();
				config.put(configItem.getType(), mapConfig);
			}

			if (configSelections.containsKey(configItem.getType())) {
				vConfigSelection = configSelections.get(configItem.getType());
			} else {
				vConfigSelection = new Vector<String>();
				configSelections.put(configItem.getType(), vConfigSelection);
			}

			vConfigSelection.add(configItem.getValue() + "|"
					+ configItem.getKey());

			mapConfig.put(configItem.getKey(), configItem);
		} catch (Exception e) {
			Logging.logError(e);
		}
	}
	/*
	 * private void addConfig(ViewEntry entry) { try { String type = (String)
	 * entry.getColumnValues().elementAt(0); String key = (String)
	 * entry.getColumnValues().elementAt(2); String value = (String)
	 * entry.getColumnValues().elementAt(3); String params = (String)
	 * entry.getColumnValues().elementAt(5);
	 * 
	 * addConfig(type, key, value, params);
	 * 
	 * } catch (Exception e) { Util.logError(e); } }
	 */
}
