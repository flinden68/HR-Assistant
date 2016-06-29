package ch.belsoft.hrassistant.config.model;

import ch.belsoft.hrassistant.config.model.ConfigType;

public interface IConfiguration {

	public int getSort();

	public void setSort(int sort);

	public ConfigType getType();

	public void setType(ConfigType type);

	public String getConfigKey();

	public void setConfigKey(String configKey);

	public String getConfigValue();

	public void setConfigValue(String configValue);

}
