package ch.belsoft.hrassistant.job.model;

import java.io.Serializable;
import java.util.Date;

import ch.belsoft.hrassistant.model.DataItem;

public class JobApplication extends DataItem implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Job job = null;
    private Person applicant = null;
    private Date date = new Date();
    private String coverLetter = "";
    private String jobId = "";
    private final DataType dataType = DataType.JOBAPPLICATION;
    
    public boolean isHasJobId(){
        return !"".equals(jobId);
    }
    
    public Job getJob() {
        return job;
    }
    
    public void setJob(Job job) {
        this.job = job;
    }
    
    public Person getApplicant() {
        return applicant;
    }
    
    public void setApplicant(Person applicant) {
        this.applicant = applicant;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public String getCoverLetter() {
        return coverLetter;
    }
    
    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }
    
    public DataType getDataType() {
        return dataType;
    }
    
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
    
    public String getJobId() {
        return jobId;
    }
}
