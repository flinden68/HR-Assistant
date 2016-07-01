package ch.belsoft.hrassistant.company.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ch.belsoft.hrassistant.company.dao.CompanyDAO;
import ch.belsoft.hrassistant.controller.ApplicationController;
import ch.belsoft.hrassistant.controller.ControllerBase;
import ch.belsoft.hrassistant.controller.IGuiController;
import ch.belsoft.hrassistant.job.model.Address;
import ch.belsoft.hrassistant.job.model.Company;
import ch.belsoft.tools.Logging;
import ch.belsoft.tools.XPagesUtil;

public class CompanyController extends ControllerBase implements IGuiController<Company>, Serializable {
    
    private static final long serialVersionUID = 1L;
    private static final String PAGETITLE_NEW = "New Company";
    private static final String PAGETITLE_EXISTING = "Company: {NAME}";
    private static final String PAGETITLE_REPLACE_NAME = "{NAME}";
    private static final String PAGETITLE_LIST_ALL = "All companies";
    private static final String PAGETITLE_LIST_SPECIFIC = "Other companies {NAME}";
    private CompanyDAO companyDAO = new CompanyDAO();
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
                } else {
                    newDataItem = true;
                    this.company = new Company();
                    this.company.setAddress(new Address());
                    
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
            if (this.newDataItem) {
                this.companyDAO.create(company);
                XPagesUtil.redirect("company.xsp?openxpage&id=" + company.getId());
            } else {
                Logging.logEvent("updating.. rev:" + company.getRev() + " id:"
                        + company.getId());
                this.companyDAO.update(company);
                this.company = companyDAO.read(company.getId());
            }
            
        } catch (Exception e) {
            handleException(e);
            Logging.logError(e);
        }
    }
    
    public void clearCompanies() {
        this.companies = new ArrayList<Company>();
    }
    
    public List<Company> getCompanies() {
        
        try {
            if (this.companies.size() == 0) {
                this.companies = this.companyDAO.read();
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
}
