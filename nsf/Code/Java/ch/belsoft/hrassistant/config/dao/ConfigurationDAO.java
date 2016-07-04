package ch.belsoft.hrassistant.config.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nl.elstarit.cloudant.model.ConnectorIndex;

import ch.belsoft.hrassistant.config.model.ConfigDefault;
import ch.belsoft.hrassistant.dao.BaseDAO;
import ch.belsoft.hrassistant.dao.ICrudDAO;
import ch.belsoft.tools.Logging;
import ch.belsoft.tools.XPagesUtil;

public class ConfigurationDAO extends BaseDAO implements
		ICrudDAO<ConfigDefault, String>, Serializable {
	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private static final String DESIGN_DOC = "configuration";
	private static final String VIEW_NAME = "configurations";
	private static final int VIEW_LIMIT = 1000;
	private static final String SEARCH_PATTERN = "configValue:{QUERY} OR configKey:{QUERY}";

	public void update(ConfigDefault config) {
		connectToService();
		super.updateModifiedDate(config);
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
		return (List<ConfigDefault>) cloudantService.findAllDocumentFromView(
				ConfigDefault.class, DESIGN_DOC, VIEW_NAME, "STRING",
				VIEW_LIMIT);
	}

	public List<ConfigDefault> readWithKeys(String startKey, String endKey) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ConfigDefault> search(String query) {
		List<ConfigDefault> result = new ArrayList<ConfigDefault>();
		try {
			// TODO: search within the CLOUDANT DB..

			if (true) {
				connectToService();
				String queryFinal = SEARCH_PATTERN.replace(SEARCH_QUERYREPLACE,
						query);
				result = (List<ConfigDefault>) cloudantService.search(
						"configuration/ftsearchConfigurations",
						ConfigDefault.class, 100, queryFinal);

				// System.out.println("result:" + result);
				/*
				 * for (ConnectorIndex index : cloudantService.allIndices()) {
				 * System.out.println("index DesignDocId" +
				 * index.getDesignDocId()); System.out.println("index Name" +
				 * index.getName()); if (index.getDesignDocId() != null) {
				 * cloudantService.search(index.getDesignDocId(),
				 * ConfigDefault.class, 500, "configValue:male"); } }
				 */
			} else {
				query = query.toLowerCase();
				for (ConfigDefault config : this.read()) {
					if ((config.getConfigKey() + "@" + config.getConfigValue())
							.toLowerCase().contains(query)) {
						result.add(config);
					}
				}
			}

			// result =

		} catch (Exception e) {
			Logging.logError(e);
		}
		return result;
	}
}
