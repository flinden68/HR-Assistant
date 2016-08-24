package ch.belsoft.hrassistant.job.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.faces.model.SelectItem;

import ch.belsoft.hrassistant.attachment.model.AttachmentHolder;
import ch.belsoft.hrassistant.company.dao.CompanyDAO;
import ch.belsoft.hrassistant.config.model.ConfigType;
import ch.belsoft.hrassistant.controller.ApplicationController;
import ch.belsoft.hrassistant.controller.ControllerBase;
import ch.belsoft.hrassistant.controller.IGuiController;
import ch.belsoft.hrassistant.job.dao.JobDAO;
import ch.belsoft.hrassistant.job.model.Address;
import ch.belsoft.hrassistant.job.model.Company;
import ch.belsoft.hrassistant.job.model.Job;
import ch.belsoft.hrassistant.job.model.Person;
import ch.belsoft.tools.Logging;
import ch.belsoft.tools.XPagesUtil;

import com.ibm.bluemix.services.watson.alchemylanguage.injector.AlchemyLanguageInjector;
import com.ibm.bluemix.services.watson.alchemylanguage.interfaces.AlchemyLanguageController;
import com.ibm.bluemix.services.watson.toneanalyzer.injector.ToneAnalyzerInjector;
import com.ibm.bluemix.services.watson.toneanalyzer.interfaces.ToneAnalyzableController;

public class JobController extends ControllerBase implements
IGuiController<Job>, ToneAnalyzableController, AlchemyLanguageController, Serializable {
    
    private static final long serialVersionUID = 1L;
    private static final String PAGETITLE_NEW = "New Job";
    private static final String PAGETITLE_EXISTING = "Job: {NAME}({COMPANY})";
    private static final String PAGETITLE_REPLACE_NAME = "{NAME}";
    private static final String PAGETITLE_REPLACE_COMPANY = "{COMPANY}";
    private static final String PAGETITLE_JOBLIST_ALL = "All jobs";
    
    private JobDAO jobDAO = new JobDAO();
    private CompanyDAO companyDAO = new CompanyDAO();
    private Job job = null;
    private List<Job> jobs = new ArrayList<Job>();
    private List<Company> companies;
    private String searchQueryListing;
    
    private ToneAnalyzerInjector toneAnalyzerInjector = null;
    private AlchemyLanguageInjector alchemyLanguageInjector = null;
    
    public JobController() {
        super();
    }
    
    public Job getDataContext() {
        try {
            if (this.job == null) {
                String id = this.getId();
                if (!id.equals("")) {
                    read(id);
                } else {
                    newDataItem = true;
                    this.job = new Job();
                    Company company = new Company();
                    company.setAddress(new Address());
                    job.setCompany(company);
                    Person person = new Person();
                    person.setAddress(new Address());
                    job.setRepresentative(person);
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
    
    public void apply(Job job) {
        XPagesUtil
        .redirect("jobapplication.xsp?openxpage&jobid=" + job.getId());
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
    
    private void read(String id) {
        this.job = jobDAO.read(id);
        loadCompany();
    }
    
    public void update() {
        try {
            if (this.newDataItem) {
                this.jobDAO.create(job);
                XPagesUtil.redirect("job.xsp?openxpage&id=" + job.getId());
            } else {
                this.jobDAO.update(job);
                read(job.getId());
            }
            
        } catch (Exception e) {
            handleException(e);
            Logging.logError(e);
        }
    }
    
    public void clearJobs() {
        this.jobs = new ArrayList<Job>();
    }
    
    public void clearDataItemList() {
        this.jobs = new ArrayList<Job>();
    }
    
    public int getSearchResultCount() {
        return super.getListCount(this.jobs);
    }
    
    public List<Job> getJobs() {
        
        try {
            if (this.jobs.isEmpty()) {
                if (this.searchQuery.equals("")) {
                    this.jobs = this.jobDAO.read();
                } else {
                    this.jobs = this.jobDAO.search(this.searchQuery);
                }
                if (jobs != null) {
                    jobs.remove(this.job);
                }
            }
            
        } catch (Exception e) {
            Logging.logError(e);
        }
        return this.jobs;
    }
    
    private void loadCompany() {
        job.setCompany(companyDAO.read(job.getCompanyId()));
    }
    
    public void changeCompany() {
        loadCompany();
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
    
    public List<SelectItem> getCompanySelection() {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        companies = companyDAO.read();
        for (Company comp : companies) {
            selectItems.add(new SelectItem(comp.getId(), comp.getName()));
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
    
    public List<String> getCountrySelection() {
        jobs = getJobs();
        List<String> selectItems = new ArrayList<String>();
        Set<String> countries = new TreeSet<String>();
        for (Job job : jobs) {
            countries.add(job.getCompany().getAddress().getCountry());
        }
        selectItems.addAll(countries);
        Collections.sort(selectItems);
        return selectItems;
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
    
    public void setJob(Job job) {
        this.job = job;
    }
    
    public Job getJob() {
        return job;
    }
    
    public CompanyDAO getCompanyDAO() {
        return companyDAO;
    }
    
    public void setCompanyDAO(CompanyDAO companyDAO) {
        this.companyDAO = companyDAO;
    }
    
    public void setSearchQueryListing(String searchQueryListing) {
        this.searchQueryListing = searchQueryListing;
    }
    
    public String getSearchQueryListing() {
        return searchQueryListing;
    }
    
    public void analyzeText() {
        try {
            this.toneAnalyzerInjector.analyzeTone(this.job);
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
    
    public void analyzeTextAlchemyLanguage() {
        try {
            this.alchemyLanguageInjector.analyzeText(this.job);
        } catch (Exception e) {
            Logging.logError(e);
        }
    }
    
    public AlchemyLanguageInjector getAlchemyLanguageInjector() {
        // TODO Auto-generated method stub
        return alchemyLanguageInjector;
    }
    
    public void setAlchemyLanguageInjector(
            AlchemyLanguageInjector alchemyLanguageInjector) {
        this.alchemyLanguageInjector = alchemyLanguageInjector;
        
    }
    
}
