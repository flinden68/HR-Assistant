package ch.belsoft.hrassistant.job.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ch.belsoft.hrassistant.dao.BaseDAO;
import ch.belsoft.hrassistant.dao.ICrudDAO;
import ch.belsoft.hrassistant.job.model.JobApplication;
import ch.belsoft.tools.Logging;

public class JobApplicationDAO extends BaseDAO implements
		ICrudDAO<JobApplication, String>, Serializable {

	private static final long serialVersionUID = 1L;
	private static final String DESIGN_DOC = "jobapplication";
	private static final String VIEW_NAME = "jobapplications";
	private static final String DESIGN_DOC_KEYS = "jobapplicationkey";
	private static final String VIEW_NAME_KEYS = "jobapplicationkeys";
	private static final String SEARCH_PATTERN = "lastname: {QUERY} OR firstname: {QUERY} OR jobname:{QUERY} OR companyname:{QUERY} OR companystreet:{QUERY} OR companystreetnumber:{QUERY} OR companyzip:{QUERY} OR companycity:{QUERY} OR companycountry:{QUERY} OR jobtype:{QUERY} OR careerlevel:{QUERY} OR industry:{QUERY}";
	private static final String SEARCH_INDEX = "jobapplication/ftsearchJobApplications";
	private static final int VIEW_LIMIT = 1000;
	private static final int SEARCH_COUNT = 100;

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
		super.updateModifiedDate(t);
		super.handleResponse(cloudantService.updateDocument(t), t);
	}

	@SuppressWarnings("unchecked")
	public List<JobApplication> readWithKeys(String startKey, String endKey) {
		connectToService();
		return (List<JobApplication>) cloudantService
				.findAllDocumentFromViewKeys(JobApplication.class,
						DESIGN_DOC_KEYS, VIEW_NAME_KEYS, "STRING", VIEW_LIMIT,
						startKey, endKey);
	}

	@SuppressWarnings("unchecked")
	public List<JobApplication> search(String query) {
		List<JobApplication> result = new ArrayList<JobApplication>();
		try {
			connectToService();
			String queryFinal = SEARCH_PATTERN.replace(SEARCH_QUERYREPLACE,
					query);
			result = (List<JobApplication>) cloudantService.search(
					SEARCH_INDEX, JobApplication.class, SEARCH_COUNT,
					queryFinal);

		} catch (Exception e) {
			Logging.logError(e);
		}

		return result;

	}

	public List<JobApplication> searchByJob(String jobId) {
		List<JobApplication> result = new ArrayList<JobApplication>();
		try {
			// TODO replace the java filter through a DB search..
			List<JobApplication> tmpResult = this.read();
			Iterator<JobApplication> it = tmpResult.iterator();
			while (it.hasNext()) {
				JobApplication appl = (JobApplication) it.next();
				if (appl.getJobId().equals(jobId)) {
					result.add(appl);
				}
			}
		} catch (Exception e) {
			Logging.logError(e);
		}

		return result;
	}

}
