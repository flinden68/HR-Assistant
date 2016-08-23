package ch.belsoft.hrassistant.job.model;

import java.io.Serializable;
import java.util.Date;

import ch.belsoft.hrassistant.model.DataItem;

import com.ibm.bluemix.services.watson.toneanalyzer.interfaces.ToneAnalyzable;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneAnalyzerResult;
import com.ibm.xsp.http.MimeMultipart;

/**
 * @author FDN
 * 
 */
public class Job extends DataItem implements ToneAnalyzable, Serializable {
    
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
    private MimeMultipart description;
    private String descriptionAbstract;
    private Date startDate;
    private ToneAnalyzerResult toneAnalyzerResult;
    
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
    
    public boolean isHasCompanyId() {
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
    
    public MimeMultipart getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = new MimeMultipart(description);
    }
    
    public void setDescription(MimeMultipart description) {
        this.description = description;
    }
    
    public String getDescriptionAbstract() {
        return descriptionAbstract;
    }
    
    public void setDescriptionAbstract(String descriptionAbstract) {
        this.descriptionAbstract = descriptionAbstract;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    
    public ToneAnalyzerResult getToneAnalyzerResult() {
        return toneAnalyzerResult;
    }
    
    public void setToneAnalyzerResult(ToneAnalyzerResult toneAnalyzerResult) {
        this.toneAnalyzerResult = toneAnalyzerResult;
    }
    
    public String getTextToAnalyze() {
        StringBuilder sb = new StringBuilder(this.name);
        sb.append('\n');
        sb.append(this.descriptionAbstract);
        sb.append(this.description.getHTML());
        return sb.toString();
    }
    
    public void setTextToAnalyze(String textToAnalyze) {
        // no setter needed.
        return;
    }
    
}
