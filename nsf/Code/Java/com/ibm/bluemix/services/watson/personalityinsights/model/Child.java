package com.ibm.bluemix.services.watson.personalityinsights.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Child {

	private String id;
	private String name;
	private String category;
	private float percentage = 0f;

	@JsonProperty("sampling_error")
	private float samplingError = 0f;

	private List<Child> children = new ArrayList<Child>();

	public String getId() {
		return id;
	}

	public boolean hasChildren(){
		return children.size()>0;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public List<Child> getChildren() {
		return children;
	}

	public void setChildren(List<Child> children) {
		this.children = children;
	}

	public float getSamplingError() {
		return samplingError;
	}

	public void setSamplingError(float samplingError) {
		this.samplingError = samplingError;
	}

}
