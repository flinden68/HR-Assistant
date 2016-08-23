package com.ibm.bluemix.services.watson.personalityinsights.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonalityInsightsResult {

	private String id;
	private String source;

	@JsonProperty("word_count")
	private String wordCount;

	@JsonProperty("word_count_message")
	private String wordCountMessage;

	@JsonProperty("processed_lang")
	private String processedLang;

	public PersonalityInsightsResult() {

	}

}
