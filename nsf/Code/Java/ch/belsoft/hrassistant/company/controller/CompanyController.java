package ch.belsoft.hrassistant.company.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ch.belsoft.hrassistant.attachment.controller.AttachmentController;
import ch.belsoft.hrassistant.attachment.model.Attachment;
import ch.belsoft.hrassistant.attachment.model.AttachmentHolder;
import ch.belsoft.hrassistant.attachment.model.Upload;
import ch.belsoft.hrassistant.company.dao.CompanyDAO;
import ch.belsoft.hrassistant.controller.ApplicationController;
import ch.belsoft.hrassistant.controller.ControllerBase;
import ch.belsoft.hrassistant.controller.IGuiController;
import ch.belsoft.hrassistant.job.dao.JobDAO;
import ch.belsoft.hrassistant.job.model.Address;
import ch.belsoft.hrassistant.job.model.Company;
import ch.belsoft.hrassistant.job.model.Job;
import ch.belsoft.tools.Logging;
import ch.belsoft.tools.XPagesUtil;

public class CompanyController extends ControllerBase implements IGuiController<Company>, Serializable {
    
    private static final long serialVersionUID = 1L;
    private static final String PAGETITLE_NEW = "New Company";
    private static final String PAGETITLE_EXISTING = "Company: {NAME}";
    private static final String PAGETITLE_REPLACE_NAME = "{NAME}";
    private static final String PAGETITLE_LIST_ALL = "All companies";
    
    private CompanyDAO companyDAO = new CompanyDAO();
    private JobDAO jobDAO = new JobDAO();
    private Company company = null;
    private List<Company> companies = new ArrayList<Company>();
    
    public CompanyController(){
    }
    
    public Company getDataContext() {
        try {
            if (this.company == null) {
                String id = this.getId();
                if (!id.equals("")) {
                    this.company = companyDAO.read(id);
                    if(!"".equals(company.getAttachmentId())){
                        loadAttachmnents(company);
                    }
                    this.upload = new Upload();
                    
                } else {
                    newDataItem = true;
                    this.company = new Company();
                    this.company.setAddress(new Address());
                    
                    this.upload = new Upload();
                    
                    
                }
            }
        } catch (Exception e) {
            Logging.logError(e);
        }
        return this.company;
    }
    
    public String getPageTitle() {
        String pageTitle = "";
        try {
            if (this.newDataItem) {
                pageTitle = PAGETITLE_NEW;
            } else {
                pageTitle = PAGETITLE_EXISTING.replace(PAGETITLE_REPLACE_NAME,
                        this.company.getName());
            }
        } catch (Exception e) {
            Logging.logError(e);
        }
        return pageTitle;
    }
    
    public String getListTitle() {
        String result = "";
        try {
            result = PAGETITLE_LIST_ALL;
        } catch (Exception e) {
            Logging.logError(e);
        }
        return result;
    }
    
    public void remove(Company company) {
        try {
            removeAttachments();
            this.companyDAO.delete(company);
        } catch (Exception e) {
            Logging.logError(e);
        }
    }
    
    public void remove() {
        try {
            this.remove(this.company);
            
        } catch (Exception e) {
            Logging.logError(e);
        }
    }
    
    public void removeFromList(Company company) {
        try {
            this.remove(company);
            this.companies.remove(company);
        } catch (Exception e) {
            Logging.logError(e);
        }
    }
    
    public void update() {
        try {
            updateAttachments(company);
            if (this.newDataItem) {
                this.companyDAO.create(company);
                XPagesUtil.redirect("company.xsp?openxpage&id=" + company.getId());
            } else {
                Logging.logEvent("updating.. rev:" + company.getRev() + " id:" + company.getId());
                this.companyDAO.update(company);
                this.company = companyDAO.read(company.getId());
            }
            
        } catch (Exception e) {
            handleException(e);
            Logging.logError(e);
        }
    }
    
    public void removeAttachment(Attachment attachment){
        attachmentHolder.getAttachments().remove(attachment.getName());
        if(attachmentHolder.getAttachments().isEmpty()){
            removeAttachments();
            company.setAttachmentId("");
            this.companyDAO.update(company);
            removeAttachmentIdFromJobs();
        }else{
            attachmentController.update(attachmentHolder);
            loadAttachmnents(company);
        }
    }
    
    public void removeAttachmentIdFromJobs(){
        if(company.getId()!=null && !"".equals(company.getId())){
            List<Job> jobs = jobDAO.searchByCompanyId(company.getId());
            for(Job job : jobs){
                job.getCompany().setAttachmentId("");
                jobDAO.update(job);
            }
        }
    }
    
    public void clearCompanies() {
        this.companies = new ArrayList<Company>();
    }
    
    public void clearDataItemList() {
        this.companies = new ArrayList<Company>();
    }
    
    public int getSearchResultCount() {
        return super.getListCount(this.companies);
    }
    
    public List<Company> getCompanies() {
        
        try {
            if (this.companies.isEmpty()) {
                if (this.searchQuery.equals("")) {
                    this.companies = this.companyDAO.read();
                    
                } else {
                    this.companies = this.companyDAO
                    .search(this.searchQuery);
                }
                if(companies != null){
                    companies.remove(company);
                }
            }
            
        } catch (Exception e) {
            Logging.logError(e);
        }
        return this.companies;
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
    
    public void setCompanyDAO(CompanyDAO companyDAO) {
        this.companyDAO = companyDAO;
    }
    
    public CompanyDAO getCompanyDAO() {
        return companyDAO;
    }
    
    public void setCompany(Company company) {
        this.company = company;
    }
    
    public Company getCompany() {
        return company;
    }
    
    @Override
    public AttachmentController getAttachmentController() {
        return attachmentController;
    }
    
    @Override
    public void setAttachmentController(AttachmentController attachmentController) {
        this.attachmentController = attachmentController;
    }
    
    @Override
    public void setUpload(Upload upload) {
        this.upload = upload;
    }
    
    @Override
    public Upload getUpload() {
        return upload;
    }
    
    @Override
    public AttachmentHolder getAttachmentHolder() {
        return attachmentHolder;
    }
    
    @Override
    public void setAttachmentHolder(AttachmentHolder attachmentHolder) {
        this.attachmentHolder = attachmentHolder;
    }
    
    public JobDAO getJobDAO() {
        return jobDAO;
    }
    
    public void setJobDAO(JobDAO jobDAO) {
        this.jobDAO = jobDAO;
    }
    
}
