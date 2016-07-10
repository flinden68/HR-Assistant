package ch.belsoft.hrassistant.config.model;

import ch.belsoft.hrassistant.config.model.ConfigType;
import ch.belsoft.hrassistant.model.DataItem;
import ch.belsoft.tools.Logging;
import java.util.HashMap;

public class ConfigDefault extends DataItem implements IConfiguration {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ConfigType type = null;
	private int sort = 0;
	private String configKey = "";
	private String configValue = "";
	private DataType dataType = DataType.CONFIG;
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
			this.setType(ConfigType.valueOf(type));
		} catch (Exception e) {
			Logging.logError(e);
		}
	}

	public String getTypeString() {

		if (this.type != null) {
			// System.out.println("get type: " + this.type);
			return this.type.toString();
		} else {
			// System.out.println("get type 2: " + this.type);
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
		System.out.println(this.hashCode() + " inside setConfigValue:"
				+ configValue);
		this.configValue = configValue;
	}

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
	
	public String getParamValue(ConfigParams configParams){
		return this.getParam(configParams).getParamValue();
	}
}
