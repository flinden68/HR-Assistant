package ch.belsoft.hrassistant.job.model;

import java.io.Serializable;
import java.util.Date;

import ch.belsoft.hrassistant.model.DataItem;

import com.ibm.bluemix.services.watson.alchemylanguage.interfaces.AlchemyLanguageAnalyzable;
import com.ibm.bluemix.services.watson.alchemylanguage.model.AlchemyLanguageResult;
import com.ibm.bluemix.services.watson.personalityinsights.interfaces.PersonalityInsightable;
import com.ibm.bluemix.services.watson.personalityinsights.model.PersonalityInsightsResult;
import com.ibm.bluemix.services.watson.toneanalyzer.interfaces.ToneAnalyzable;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneAnalyzerResult;

public class JobApplication extends DataItem implements ToneAnalyzable,
PersonalityInsightable, AlchemyLanguageAnalyzable, Serializable {
    
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
    private ToneAnalyzerResult toneAnalyzerResult;
    private PersonalityInsightsResult personalityInsightsResult;
    private AlchemyLanguageResult alchemyLanguageResult;
    
    public boolean isHasJobId() {
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
    
    public String getName() {
        return new StringBuilder(this.applicant.getFirstName()).append(" ")
        .append(this.applicant.getLastName()).toString();
    }
    
    public String getTextToAnalyze() {
        return this.coverLetter;
    }
    
    public ToneAnalyzerResult getToneAnalyzerResult() {
        return toneAnalyzerResult;
    }
    
    public void setToneAnalyzerResult(ToneAnalyzerResult toneAnalyzerResult) {
        this.toneAnalyzerResult = toneAnalyzerResult;
    }
    
    public void setTextToAnalyze(String textToAnalyze) {
        // TODO Auto-generated method stub
    }
    
    public PersonalityInsightsResult getPersonalityInsightsResult() {
        return personalityInsightsResult;
    }
    
    public void setPersonalityInsightsResult(
            PersonalityInsightsResult personalityInsightsResult) {
        this.personalityInsightsResult = personalityInsightsResult;
    }
    
    public AlchemyLanguageResult getAlchemyLanguageResult() {
        return alchemyLanguageResult;
    }
    
    public void setAlchemyLanguageResult(
            AlchemyLanguageResult alchemyLanguageResult) {
        this.alchemyLanguageResult = alchemyLanguageResult;
    }
    
}
