package ch.belsoft.hrassistant.config.model;

import java.io.Serializable;

import ch.belsoft.hrassistant.config.model.ConfigType;
import ch.belsoft.hrassistant.model.DataItem;

public class ConfigDefault extends DataItem implements IConfiguration,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ConfigType type = null;
	protected int sort = 0;
	protected String key = "";
	protected String value = "";

	public ConfigDefault() {

	}

	public ConfigDefault(ConfigType type, String key, String value) {
		this.type = type;
		this.key = key;
		this.value = value;
	}

	public void setTypeString(String type) {
		System.out.println(this.hashCode() + " inside getTypeString: "
				+ this.type);
		this.setType(ConfigType.valueOf(type));
	}

	public String getTypeString() {
		System.out.println(this.hashCode() + " inside getTypeString: "
				+ this.type);
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

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

}
