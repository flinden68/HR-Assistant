package ch.belsoft.hrassistant.config.dao;

import java.io.Serializable;
import java.util.List;

import ch.belsoft.hrassistant.config.model.IConfiguration;
import ch.belsoft.hrassistant.dao.ICrudDAO;
import ch.belsoft.hrassistant.service.CloudantService;

public class ConfigurationDAO implements ICrudDAO<IConfiguration, String>,
		Serializable {
	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private CloudantService cloudantService = null;

	public void update(IConfiguration config) {
		cloudantService.updateDocument(config);
	}

	public void create(IConfiguration config) {
		cloudantService.saveDocument(config);
	}

	public void delete(IConfiguration config) {
		cloudantService.removeDocument(config);
	}

	public IConfiguration read(String id) {
		return (IConfiguration) cloudantService.findDocumentByID(
				IConfiguration.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<IConfiguration> read() {
		return (List<IConfiguration>) cloudantService
				.findAllDocuments(IConfiguration.class);
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
