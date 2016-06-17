package ch.belsoft.hrassistant.config.model;

import java.io.Serializable;

import ch.belsoft.hrassistant.config.model.ConfigType;
import ch.belsoft.hrassistant.model.DataItem;

public class ConfigDefault extends DataItem implements IConfiguration,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8693716780920880271L;
	protected ConfigType type = null;
	protected String key = "";
	protected String value = "";

	public ConfigDefault() {

	}

	public ConfigDefault(ConfigType type, String key, String value) {
		this.type = type;
		this.key = key;
		this.value = value;
	}

	public ConfigType getType() {
		return type;
	}

	public void setType(ConfigType type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String toString() {
		return this.type + "@" + this.key + "@" + this.value;
	}

}
