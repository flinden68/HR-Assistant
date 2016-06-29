package ch.belsoft.hrassistant.config.dao;

import java.io.Serializable;
import java.util.List;

import ch.belsoft.hrassistant.config.model.ConfigDefault;
import ch.belsoft.hrassistant.config.model.IConfiguration;
import ch.belsoft.hrassistant.dao.ICrudDAO;
import ch.belsoft.hrassistant.service.CloudantService;

public class ConfigurationDAO implements ICrudDAO<ConfigDefault, String>,
		Serializable {
	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private CloudantService cloudantService = null;
	private static final String DESIGN_DOC = "configuration";
	private static final String VIEW_NAME = "configurations";

	public void update(ConfigDefault config) {
		connectToService();
		cloudantService.updateDocument(config);
	}

	public void create(ConfigDefault config) {
		connectToService();
		cloudantService.saveDocument(config);
	}

	public void delete(ConfigDefault config) {
		connectToService();
		cloudantService.removeDocument(config);
	}

	public ConfigDefault read(String id) {
		connectToService();
		return (ConfigDefault) cloudantService.findDocumentByID(
				ConfigDefault.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<ConfigDefault> read() {
		connectToService();
		return (List<ConfigDefault>) cloudantService
				.findAllDocuments(ConfigDefault.class);
	}

	public void connectToService() {
		if (!cloudantService.isConnected()) {
			cloudantService.connect();
		}
	}

	public CloudantService getCloudantService() {
		return cloudantService;
	}

	public void setCloudantService(CloudantService cloudantService) {
		this.cloudantService = cloudantService;
	}

}
