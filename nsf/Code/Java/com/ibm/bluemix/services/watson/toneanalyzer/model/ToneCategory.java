package com.ibm.bluemix.services.watson.toneanalyzer.model;

import java.util.List;

public class ToneCategory {

	private String category_id = "";
	private String category_name = "";

	private List<Tone> tones = null;

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public List<Tone> getTones() {
		return tones;
	}

	public void setTones(List<Tone> tones) {
		this.tones = tones;
	}

}
