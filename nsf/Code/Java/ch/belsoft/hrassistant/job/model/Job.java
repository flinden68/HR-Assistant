package ch.belsoft.hrassistant.job.model;

import java.io.Serializable;

import ch.belsoft.hrassistant.model.DataItem;

public class Job extends DataItem implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String name = "";
    private Company company = null;
    private Person representative = null;
    private String companyId = "";
    private String representativeId = "";
    private final DataType dataType = DataType.JOB;
    
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
    
}
