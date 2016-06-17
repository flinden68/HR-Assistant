package ch.belsoft.hrassistant.job.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import ch.belsoft.hrassistant.config.model.ConfigType;
import ch.belsoft.hrassistant.model.DataItem;

public class JobApplication extends DataItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Job job = null;
	private Applicant applicant = null;
	private Date date = null;
	private String coverLetter = "";

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
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

}
