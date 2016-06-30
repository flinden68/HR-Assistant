package ch.belsoft.hrassistant.config.dao;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import nl.elstarit.cloudant.model.ConnectorResponse;

import ch.belsoft.hrassistant.config.model.ConfigDefault;
import ch.belsoft.hrassistant.config.model.IConfiguration;
import ch.belsoft.hrassistant.dao.BaseDAO;
import ch.belsoft.hrassistant.dao.ICrudDAO;
import ch.belsoft.hrassistant.service.CloudantService;
import ch.belsoft.tools.Logging;

public class ConfigurationDAO extends BaseDAO implements
		ICrudDAO<ConfigDefault, String>, Serializable {
	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private static final String DESIGN_DOC = "configuration";
	private static final String VIEW_NAME = "configurations";

	public void update(ConfigDefault config) {
		connectToService();
		super.handleResponse(cloudantService.updateDocument(config), config);
	}

	public void create(ConfigDefault config) {
		connectToService();
		super.handleResponse(cloudantService.saveDocument(config), config);
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

}
