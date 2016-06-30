package ch.belsoft.hrassistant.config.dao;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import nl.elstarit.cloudant.model.ConnectorResponse;

import ch.belsoft.hrassistant.config.model.ConfigDefault;
import ch.belsoft.hrassistant.config.model.IConfiguration;
import ch.belsoft.hrassistant.dao.BaseDAO;
import ch.belsoft.hrassistant.dao.ICrudDAO;
import ch.belsoft.hrassistant.job.model.Job;
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
	private static final int VIEW_LIMIT = 1000;

	public void update(ConfigDefault config) {
		connectToService();
		Logging.logEvent("rev before update: " + config.getRev()
				+ " configValue:" + config.getConfigValue() + " and id: "
				+ config.getId());
		super.handleResponse(cloudantService.updateDocument(config), config);
		Logging.logEvent("rev after update: " + config.getRev()
				+ " configValue:" + config.getConfigValue() + " and id:"
				+ config.getId());
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
		return (List<ConfigDefault>) cloudantService.findAllDocumentFromView(
				ConfigDefault.class, DESIGN_DOC, VIEW_NAME, "STRING",
				VIEW_LIMIT);
	}

}
