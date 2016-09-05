package ch.belsoft.hrassistant.config.model;

import java.util.HashMap;

import ch.belsoft.hrassistant.model.DataItem;
import ch.belsoft.tools.Logging;

public class ConfigDefault extends DataItem implements IConfiguration {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private ConfigType type = null;
	private int sort = 0;
	private String configKey = "";
	private String configValue = "";
	private final DataType dataType = DataType.CONFIG;
	private HashMap<ConfigParams, ConfigParam> params = new HashMap<ConfigParams, ConfigParam>();

	public ConfigDefault() {

	}

	public ConfigDefault(ConfigType type, String configKey, String configValue) {
		this.type = type;
		this.configKey = configKey;
		this.configValue = configValue;
	}

	public void setTypeString(String type) {
		try {
			if (type.equals("")) {
				this.setType(null);
			} else {
				this.setType(ConfigType.valueOf(type));
			}

		} catch (Exception e) {
			Logging.logError(e);
		}
	}

	public String getTypeString() {

		if (this.type != null) {
			return this.type.toString();
		} else {
			return "not defined";
		}
	}

	public ConfigType getType() {
		return type;
	}

	public void setType(ConfigType type) {
		this.type = type;
	}

	public String getConfigKey() {
		return configKey;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	@Override
	public String toString() {
		return this.type + "@" + this.configKey + "@" + this.configValue;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public DataType getDataType() {
		return dataType;
	}

	public HashMap<ConfigParams, ConfigParam> getParams() {
		return params;
	}

	public void setParams(HashMap<ConfigParams, ConfigParam> params) {
		this.params = params;
	}

	public void setParam(ConfigParams configParams, ConfigParam configParam) {
		this.params.put(configParams, configParam);
	}

	public ConfigParam getParam(ConfigParams configParams) {
		if (!this.params.containsKey(configParams)) {
			this.setParam(configParams, new ConfigParam());
		}
		return this.params.get(configParams);
	}

	public String getParamValue(ConfigParams configParams) {
		return this.getParam(configParams).getParamValue();
	}

	public String getName() {
		return getConfigKey() + ": " + getConfigValue();
	}
}
