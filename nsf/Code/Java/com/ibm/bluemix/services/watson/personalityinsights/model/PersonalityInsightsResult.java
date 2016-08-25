package com.ibm.bluemix.services.watson.personalityinsights.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonalityInsightsResult {

	private String id;
	private String source;

	@JsonProperty("word_count")
	private String wordCount;

	@JsonProperty("word_count_message")
	private String wordCountMessage;

	@JsonProperty("processed_lang")
	private String processedLang;

	private Child tree;

	// private List<Child> tree = new ArrayList<Child>();

	public PersonalityInsightsResult() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getWordCount() {
		return wordCount;
	}

	public void setWordCount(String wordCount) {
		this.wordCount = wordCount;
	}

	public String getWordCountMessage() {
		return wordCountMessage;
	}

	public void setWordCountMessage(String wordCountMessage) {
		this.wordCountMessage = wordCountMessage;
	}

	public String getProcessedLang() {
		return processedLang;
	}

	public void setProcessedLang(String processedLang) {
		this.processedLang = processedLang;
	}

	public Child getTree() {
		return tree;
	}

	public void setTree(Child tree) {
		this.tree = tree;
	}

	/*
	 * public List<Child> getTree() { return tree; }
	 * 
	 * public void setTree(List<Child> tree) { this.tree = tree; }
	 */
}
