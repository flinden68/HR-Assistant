package ch.belsoft.hrassistant.job.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ch.belsoft.hrassistant.controller.ApplicationController;
import ch.belsoft.hrassistant.controller.ControllerBase;
import ch.belsoft.hrassistant.controller.IGuiController;
import ch.belsoft.hrassistant.job.dao.JobDAO;
import ch.belsoft.hrassistant.job.model.Job;
import ch.belsoft.tools.Logging;
import ch.belsoft.tools.XPagesUtil;

public class JobController extends ControllerBase implements IGuiController<Job>, Serializable {
    
    private static final long serialVersionUID = 1L;
    private static final String PAGETITLE_NEW = "New Job";
    private static final String PAGETITLE_EXISTING = "Job: {NAME}({COMPANY})";
    private static final String PAGETITLE_REPLACE_NAME = "{NAME}";
    private static final String PAGETITLE_REPLACE_COMPANY = "{COMPANY}";
    private static final String PAGETITLE_JOBLIST_ALL = "All jobs";
    private static final String PAGETITLE_JOBLIST_COMPANY = "Other jobs of {COMPANY}";
    private JobDAO jobDAO = new JobDAO();
    private Job job = null;
    private List<Job> jobs = new ArrayList<Job>();
    
    public JobController(){}
    
    public Job getDataContext() {
        try {
            if (this.job == null) {
                String id = this.getId();
                if (!id.equals("")) {
                    this.job = jobDAO.read(id);
                } else {
                    newDataItem = true;
                    this.job = new Job();
                    
                }
            }
        } catch (Exception e) {
            Logging.logError(e);
        }
        return this.job;
    }
    
    public String getPageTitle() {
        String pageTitle = "";
        try {
            if (this.newDataItem) {
                pageTitle = PAGETITLE_NEW;
            } else {
                pageTitle = PAGETITLE_EXISTING.replace(PAGETITLE_REPLACE_NAME,
                        this.job.getName());
                pageTitle = pageTitle.replace(PAGETITLE_REPLACE_COMPANY,
                        this.job.getCompany().getName());
            }
        } catch (Exception e) {
            Logging.logError(e);
        }
        return pageTitle;
    }
    
    public String getJobListTitle() {
        String result = "";
        try {
            result = PAGETITLE_JOBLIST_ALL;
        } catch (Exception e) {
            Logging.logError(e);
        }
        return result;
    }
    
    public void remove(Job job) {
        try {
            this.jobDAO.delete(job);
        } catch (Exception e) {
            Logging.logError(e);
        }
    }
    
    public void remove() {
        try {
            this.remove(this.job);
            
        } catch (Exception e) {
            Logging.logError(e);
        }
    }
    
    public void removeFromList(Job job) {
        try {
            this.remove(job);
            this.jobs.remove(job);
        } catch (Exception e) {
            Logging.logError(e);
        }
    }
    
    public void update() {
        try {
            if (this.newDataItem) {
                this.jobDAO.create(job);
                XPagesUtil.redirect("job.xsp?openxpage&id="
                        + job.getId());
            } else {
                Logging.logEvent("updating.. rev:" + job.getRev() + " id:"
                        + job.getId());
                this.jobDAO.update(job);
                this.job = jobDAO.read(job.getId());
            }
            
        } catch (Exception e) {
            handleException(e);
            Logging.logError(e);
        }
    }
    
    public void clearJobs() {
        this.jobs = new ArrayList<Job>();
    }
    
    public List<Job> getJobs() {
        
        try {
            if (this.jobs.size() == 0) {
                this.jobs = this.jobDAO.read();
            }
            
        } catch (Exception e) {
            Logging.logError(e);
        }
        return this.jobs;
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
    
    public void setJob(Job job) {
        this.job = job;
    }
    
    public Job getJob() {
        return job;
    }
    
}
