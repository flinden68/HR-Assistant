package ch.belsoft.hrassistant.job.dao;

import java.io.Serializable;
import java.util.List;

import nl.elstarit.cloudant.model.ConnectorResponse;
import ch.belsoft.hrassistant.dao.BaseDAO;
import ch.belsoft.hrassistant.dao.ICrudDAO;
import ch.belsoft.hrassistant.job.model.JobApplication;
import ch.belsoft.hrassistant.service.CloudantService;

public class JobApplicationDAO extends BaseDAO implements
		ICrudDAO<JobApplication, String>, Serializable {

	private static final long serialVersionUID = 1L;
	private static final String DESIGN_DOC = "jobapplication";
	private static final String VIEW_NAME = "jobapplications";
	private static final int VIEW_LIMIT = 1000;

	public JobApplicationDAO() {

	}

	public void create(JobApplication t) {
		connectToService();
		super.handleResponse(cloudantService.saveDocument(t), t);
	}

	public void delete(JobApplication t) {
		connectToService();
		cloudantService.removeDocument(t);
	}

	public JobApplication read(String id) {
		connectToService();
		return (JobApplication) cloudantService.findDocumentByID(
				JobApplication.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<JobApplication> read() {
		connectToService();
		return (List<JobApplication>) cloudantService.findAllDocumentFromView(
				JobApplication.class, DESIGN_DOC, VIEW_NAME, "STRING",
				VIEW_LIMIT);
	}

	public void update(JobApplication t) {
		connectToService();
		super.handleResponse(cloudantService.updateDocument(t), t);
	}

}
