package ch.belsoft.hrassistant.job.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.model.SelectItem;

import ch.belsoft.hrassistant.attachment.model.Attachment;
import ch.belsoft.hrassistant.attachment.model.AttachmentHolder;
import ch.belsoft.hrassistant.attachment.model.Upload;
import ch.belsoft.hrassistant.config.model.ConfigType;
import ch.belsoft.hrassistant.controller.ApplicationController;
import ch.belsoft.hrassistant.controller.ControllerBase;
import ch.belsoft.hrassistant.controller.IGuiController;
import ch.belsoft.hrassistant.job.dao.JobApplicationDAO;
import ch.belsoft.hrassistant.job.dao.JobDAO;
import ch.belsoft.hrassistant.job.model.Address;
import ch.belsoft.hrassistant.job.model.Job;
import ch.belsoft.hrassistant.job.model.JobApplication;
import ch.belsoft.hrassistant.job.model.Person;
import ch.belsoft.tools.Logging;
import ch.belsoft.tools.XPagesUtil;

import com.ibm.bluemix.services.watson.alchemylanguage.injector.AlchemyLanguageInjector;
import com.ibm.bluemix.services.watson.alchemylanguage.interfaces.AlchemyLanguageController;
import com.ibm.bluemix.services.watson.personalityinsights.injector.PersonalityInsightsInjector;
import com.ibm.bluemix.services.watson.personalityinsights.interfaces.PersonalityInsightableController;
import com.ibm.bluemix.services.watson.toneanalyzer.injector.ToneAnalyzerInjector;
import com.ibm.bluemix.services.watson.toneanalyzer.interfaces.ToneAnalyzableController;

public class JobApplicationController extends ControllerBase implements
		IGuiController<JobApplication>, ToneAnalyzableController,
		PersonalityInsightableController, AlchemyLanguageController,
		Serializable {

	private static final long serialVersionUID = 1L;
	private static final String PAGETITLE_NEW = "New Job application: {NAME}({COMPANY})";
	private static final String PAGETITLE_EXISTING = "Job application: {NAME}({COMPANY})";
	private static final String PAGETITLE_REPLACE_NAME = "{NAME}";
	private static final String PAGETITLE_REPLACE_COMPANY = "{COMPANY}";
	private static final String PAGETITLE_JOBAPPLICATIONLIST_ALL = "All job applications";
	private static final String PARAM_JOB_ID = "jobid";

	private JobDAO jobDAO = new JobDAO();
	private JobApplicationDAO jobApplicationDAO = new JobApplicationDAO();
	private JobApplication jobApplication = null;
	private List<JobApplication> jobApplications = new ArrayList<JobApplication>();
	private List<Job> jobs;
	private String searchQueryListing;
	private ToneAnalyzerInjector toneAnalyzerInjector = null;
	private PersonalityInsightsInjector personalityInsightsInjector = null;
	private AlchemyLanguageInjector alchemyLanguageInjector = null;

	public JobApplicationController() {
	}

	public JobApplication getDataContext() {
		try {
			if (this.jobApplication == null) {
				String id = this.getId();
				if (!id.equals("")) {
					read(id);

					if (!"".equals(jobApplication.getAttachmentId())) {
						loadAttachmnents(jobApplication);
					}
					this.upload = new Upload();
				} else {
					newDataItem = true;
					this.jobApplication = new JobApplication();
					Person applicant = new Person();
					applicant.setAddress(new Address());
					jobApplication.setApplicant(applicant);

					this.upload = new Upload();

				}

				// get the job
				String jobId = getJobId();
				if (!"".equals(jobId)) {
					jobApplication.setJobId(jobId);
					loadJob();
				}
			}
		} catch (Exception e) {
			Logging.logError(e);
		}
		return this.jobApplication;
	}

	protected String getJobId() {
		return XPagesUtil.getQueryString(PARAM_JOB_ID);
	}

	public String getPageTitle() {
		String pageTitle = "";
		try {
			if (this.newDataItem) {
				pageTitle = PAGETITLE_NEW;
			} else {
				pageTitle = PAGETITLE_EXISTING;
			}
			pageTitle = PAGETITLE_EXISTING.replace(PAGETITLE_REPLACE_NAME,
					this.jobApplication.getJob().getName());
			pageTitle = pageTitle.replace(PAGETITLE_REPLACE_COMPANY,
					this.jobApplication.getJob().getCompany().getName());
		} catch (Exception e) {
			Logging.logError(e);
		}
		return pageTitle;
	}

	public String getJobApplicationListTitle() {
		String result = "";
		try {
			result = PAGETITLE_JOBAPPLICATIONLIST_ALL;
		} catch (Exception e) {
			Logging.logError(e);
		}
		return result;
	}

	public void remove(JobApplication jobApplication) {
		try {
			this.jobApplicationDAO.delete(jobApplication);
		} catch (Exception e) {
			Logging.logError(e);
		}
	}

	public void remove() {
		try {
			this.remove(this.jobApplication);

		} catch (Exception e) {
			Logging.logError(e);
		}
	}

	public void removeFromList(JobApplication jobApplication) {
		try {
			this.remove(jobApplication);
			this.jobApplications.remove(jobApplication);
		} catch (Exception e) {
			Logging.logError(e);
		}
	}

	private void read(String id) {
		this.jobApplication = jobApplicationDAO.read(id);
		loadJob();
	}

	public void update() {
		try {
			updateAttachments(jobApplication);

			if (this.newDataItem) {
				this.jobApplicationDAO.create(jobApplication);
				String url = "jobapplication.xsp?openxpage&id="
						+ jobApplication.getId();
				if (!"".equals(jobApplication.getJobId())) {
					url += "&jobid=" + jobApplication.getJobId();
				}
				// XPagesUtil.redirect(url); not yet...will be done with a
				// applicants portal..
			} else {
				this.jobApplicationDAO.update(jobApplication);
				read(jobApplication.getId());
			}

		} catch (Exception e) {
			handleException(e);
			Logging.logError(e);
		}
	}

	public void removeAttachment(Attachment attachment) {
		attachmentHolder.getAttachments().remove(attachment.getName());
		if (attachmentHolder.getAttachments().isEmpty()) {
			removeAttachments();
			jobApplication.setAttachmentId("");
			this.jobApplicationDAO.update(jobApplication);
		} else {
			attachmentController.update(attachmentHolder);
			loadAttachmnents(jobApplication);
		}
	}

	public void clearJobs() {
		this.jobApplications = new ArrayList<JobApplication>();
	}

	public void clearDataItemList() {
		this.jobApplications = new ArrayList<JobApplication>();
	}

	public int getSearchResultCount() {
		return super.getListCount(this.jobApplications);
	}

	/**
	 * @return all Job Applications, sorted descending by created
	 */
	public List<JobApplication> getJobApplicationsRecent() {
		return this.getJobApplicationsRecentLimited(0);
	}

	/**
	 * @param limit
	 *            amount of jobapplications returned, 0 = all
	 * @return all Job Applications, sorted descending by created
	 */
	public List<JobApplication> getJobApplicationsRecentLimited(int limit) {
		List<JobApplication> result = this.getJobApplications();
		try {
			Collections.sort(result, ControllerBase.CreatedComparator);
			if (limit > 0) {
				result = result.subList(0, limit);
			}
		} catch (Exception e) {
			Logging.logError(e);
		}
		return result;
	}

	public List<JobApplication> getJobApplications() {

		try {
			if (this.jobApplications.isEmpty()) {
				if (this.searchQuery.equals("")) {
					this.jobApplications = this.jobApplicationDAO.read();
				} else {
					this.jobApplications = this.jobApplicationDAO
							.search(this.searchQuery);
				}
				if (jobs != null) {
					jobApplications.remove(this.jobApplication);
				}
			}

		} catch (Exception e) {
			Logging.logError(e);
		}
		return this.jobApplications;
	}

	public List<JobApplication> getJobApplicationsByJob(String jobId) {
		try {
			if (this.jobApplications.isEmpty()) {
				if (this.searchQuery.equals("")) {
					this.jobApplications = this.jobApplicationDAO
							.searchByJob(jobId);
				} else {
					this.jobApplications = this.jobApplicationDAO
							.search(this.searchQuery);
				}
				if (jobs != null) {
					jobApplications.remove(this.jobApplication);
				}
			} else {

			}

		} catch (Exception e) {
			Logging.logError(e);
		}
		return this.jobApplications;
	}

	private void loadJob() {
		jobApplication.setJob(jobDAO.read(jobApplication.getJobId()));
	}

	public void changeJob() {
		loadJob();
	}

	public String getLogo(Job job) {
		if (!"".equals(job.getCompany().getAttachmentId())) {
			AttachmentHolder attachmentHolder = attachmentController
					.findAttachment(job.getCompany().getAttachmentId());
			if (attachmentHolder != null) {
				return attachmentHolder.getAttachments().entrySet().iterator()
						.next().getValue().getSrcForImageTag();
			}
		}
		return new String("hrassistent32.png");
	}

	public List<SelectItem> getJobSelection() {
		List<SelectItem> selectItems = new ArrayList<SelectItem>();
		jobs = jobDAO.read();
		for (Job job : jobs) {
			selectItems.add(new SelectItem(job.getId(), job.getName()));
		}
		return selectItems;
	}

	public void filterJoblisting() {
		if ("".equals(searchQueryListing)) {
			jobs = jobDAO.read();
		} else {
			jobs = jobDAO.searchByCountry(searchQueryListing);
		}
	}

	public List<String> getJobIndustrySelection() {
		return applicationController
				.getConfigSelection(ConfigType.JOB_INDUSTRY);
	}

	public List<String> getJobTypeSelection() {
		return applicationController.getConfigSelection(ConfigType.JOB_TYPE);
	}

	public List<String> getCareerLevelSelection() {
		return applicationController.getConfigSelection(ConfigType.CAREERLEVEL);
	}

	/*
	 * Getters and Setters
	 */
	@Override
	public ApplicationController getApplicationController() {
		return applicationController;
	}

	@Override
	public void setApplicationController(
			ApplicationController applicationController) {
		this.applicationController = applicationController;
	}

	public void setJobDAO(JobDAO jobDAO) {
		this.jobDAO = jobDAO;
	}

	public JobDAO getJobDAO() {
		return jobDAO;
	}

	public void setSearchQueryListing(String searchQueryListing) {
		this.searchQueryListing = searchQueryListing;
	}

	public String getSearchQueryListing() {
		return searchQueryListing;
	}

	public void setJobApplicationDAO(JobApplicationDAO jobApplicationDAO) {
		this.jobApplicationDAO = jobApplicationDAO;
	}

	public JobApplicationDAO getJobApplicationDAO() {
		return jobApplicationDAO;
	}

	public void setJobApplications(List<JobApplication> jobApplications) {
		this.jobApplications = jobApplications;
	}

	public void setJobApplication(JobApplication jobApplication) {
		this.jobApplication = jobApplication;
	}

	public JobApplication getJobApplication() {
		return jobApplication;
	}

	public void analyzeText() {
		try {
			this.toneAnalyzerInjector.analyzeTone(this.jobApplication);
		} catch (Exception e) {
			Logging.logError(e);
		}
	}

	public ToneAnalyzerInjector getToneAnalyzerInjector() {
		return toneAnalyzerInjector;
	}

	public void setToneAnalyzerInjector(
			ToneAnalyzerInjector toneAnalyzerInjector) {
		this.toneAnalyzerInjector = toneAnalyzerInjector;
	}

	public PersonalityInsightsInjector getPersonalityInsightsInjector() {
		return personalityInsightsInjector;
	}

	public void setPersonalityInsightsInjector(
			PersonalityInsightsInjector personalityInsightsInjector) {
		this.personalityInsightsInjector = personalityInsightsInjector;
	}

	public void analyzeTextPersonalityInsights() {
		this.personalityInsightsInjector
				.analyzePersonalityInsights(this.jobApplication);
	}

	public void analyzeTextAlchemyLanguage() {
		try {
			this.alchemyLanguageInjector.analyzeText(this.jobApplication);
		} catch (Exception e) {
			Logging.logError(e);
		}
	}

	public AlchemyLanguageInjector getAlchemyLanguageInjector() {
		return alchemyLanguageInjector;
	}

	public void setAlchemyLanguageInjector(
			AlchemyLanguageInjector alchemyLanguageInjector) {
		this.alchemyLanguageInjector = alchemyLanguageInjector;

	}

}
