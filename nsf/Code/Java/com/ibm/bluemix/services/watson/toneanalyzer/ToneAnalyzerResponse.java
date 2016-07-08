package com.ibm.bluemix.services.watson.toneanalyzer;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonRootName(value = "document_tone")
public class ToneAnalyzerResponse {

	@JsonProperty("tone_categories")
	List<ToneCategory> toneCategories = null;

}
