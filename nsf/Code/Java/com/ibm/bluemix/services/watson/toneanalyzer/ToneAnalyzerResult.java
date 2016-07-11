package com.ibm.bluemix.services.watson.toneanalyzer;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonRootName(value = "document_tone")
public class ToneAnalyzerResult {

	@JsonProperty("tone_categories")
	private List<ToneCategory> toneCategories = null;

	public List<ToneCategory> getToneCategories() {
		return toneCategories;
	}

	public void setToneCategories(List<ToneCategory> toneCategories) {
		this.toneCategories = toneCategories;
	}

}
