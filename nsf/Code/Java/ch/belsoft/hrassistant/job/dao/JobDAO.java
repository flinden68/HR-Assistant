package ch.belsoft.hrassistant.job.dao;

import java.io.Serializable;
import java.util.List;

import ch.belsoft.hrassistant.dao.BaseDAO;
import ch.belsoft.hrassistant.dao.ICrudDAO;
import ch.belsoft.hrassistant.job.model.Job;

public class JobDAO extends BaseDAO implements ICrudDAO<Job, String>,
		Serializable {

	private static final long serialVersionUID = 1L;
	private static final String DESIGN_DOC = "job";
	private static final String VIEW_NAME = "jobs";
	private static final String DESIGN_DOC_KEYS = "jobkey";
	private static final String VIEW_NAME_KEYS = "jobkeys";
	private static final int VIEW_LIMIT = 1000;

	public void create(Job t) {
		connectToService();
		super.handleResponse(cloudantService.saveDocument(t), t);
	}

	public void delete(Job t) {
		connectToService();
		cloudantService.removeDocument(t);

	}

	public Job read(String id) {
		connectToService();
		return (Job) cloudantService.findDocumentByID(Job.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Job> read() {
		connectToService();
		return (List<Job>) cloudantService.findAllDocumentFromView(Job.class,
				DESIGN_DOC, VIEW_NAME, "STRING", VIEW_LIMIT);
	}

	public void update(Job t) {
		connectToService();
		super.updateModifiedDate(t);
		super.handleResponse(cloudantService.updateDocument(t), t);
	}

	@SuppressWarnings("unchecked")
	public List<Job> readWithKeys(String startKey, String endKey) {
		connectToService();
		return (List<Job>) cloudantService.findAllDocumentFromViewKeys(
				Job.class, DESIGN_DOC_KEYS, VIEW_NAME_KEYS, "STRING",
				VIEW_LIMIT, startKey, endKey);
	}

	public List<Job> search(String query) {
		return null;
		// TODO Auto-generated method stub

	}

}
