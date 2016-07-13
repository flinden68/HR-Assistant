package ch.belsoft.hrassistant.job.model;

import java.io.Serializable;
import java.util.Date;

import ch.belsoft.hrassistant.model.DataItem;

public class Job extends DataItem implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final DataType dataType = DataType.JOB;
    private String name = "";
    private Company company = null;
    private Person representative = null;
    private String companyId = "";
    private String representativeId = "";
    private String jobType;
    private String industry;
    private String careerLevel;
    private String description;
    private Date startDate;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Company getCompany() {
        return company;
    }
    
    public void setCompany(Company company) {
        this.company = company;
    }
    
    public Person getRepresentative() {
        return representative;
    }
    
    public void setRepresentative(Person representative) {
        this.representative = representative;
    }
    
    public DataType getDataType() {
        return dataType;
    }
    
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
    
    public String getCompanyId() {
        return companyId;
    }
    
    public void setRepresentativeId(String representativeId) {
        this.representativeId = representativeId;
    }
    
    public String getRepresentativeId() {
        return representativeId;
    }
    
    public boolean isHasCompanyId(){
        return !"".equals(companyId);
    }
    
    public void setJobType(String jobType) {
        this.jobType = jobType;
    }
    
    public String getJobType() {
        return jobType;
    }
    
    public void setIndustry(String industry) {
        this.industry = industry;
    }
    
    public String getIndustry() {
        return industry;
    }
    
    public void setCareerLevel(String careerLevel) {
        this.careerLevel = careerLevel;
    }
    
    public String getCareerLevel() {
        return careerLevel;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    
    
}
