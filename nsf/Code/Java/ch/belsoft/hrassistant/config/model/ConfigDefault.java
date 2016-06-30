package ch.belsoft.hrassistant.config.model;

import ch.belsoft.hrassistant.config.model.ConfigType;
import ch.belsoft.hrassistant.model.DataItem;

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

	public ConfigDefault() {

	}

	public ConfigDefault(ConfigType type, String configKey, String configValue) {
		this.type = type;
		this.configKey = configKey;
		this.configValue = configValue;
	}

	public void setTypeString(String type) {
		this.setType(ConfigType.valueOf(type));
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
		System.out.println(this.hashCode() + " inside setConfigValue:"+configValue);
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
}
