package com.ibm.bluemix.services.watson.toneanalyzer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ToneAnalyzerRequest {

	private String text = "";

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
