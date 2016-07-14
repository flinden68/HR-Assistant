package com.ibm.bluemix.services.watson.toneanalyzer.service;

import java.io.Serializable;

import java.util.LinkedHashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneAnalyzerRequest;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneAnalyzerResult;

import ch.belsoft.charts.controller.ChartFactory;
import ch.belsoft.charts.model.Chart;

import ch.belsoft.tools.Logging;

public class ToneAnalyzerConsumer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IToneAnalyzerService toneAnalyzerService;
	private ToneAnalyzerResult toneAnalyzerResult = null;
	private LinkedHashMap<String, Chart> toneAnalyzerChart = null;

	// private String toneAnalyzerEmotionToneChart = "";
	// private String toneAnalyzerLanguageToneChart = "";
	// private String toneAnalyzerSocialToneChart = "";

	private static final String EMOTION_TONE = "emotion_tone";
	private static final String LANGUAGE_TONE = "language_tone";
	private static final String SOCIAL_TONE = "social_tone";

	private final ObjectMapper mapper = new ObjectMapper();

	public ToneAnalyzerConsumer(final IToneAnalyzerService svc) {
		this.toneAnalyzerService = svc;
	}

	public void analyzeTone(ToneAnalyzerRequest toneAnalyzerRequest) {
		try {
			this.toneAnalyzerResult = toneAnalyzerService
					.analyzeTone(toneAnalyzerRequest);
			if (toneAnalyzerResult != null) {
				this.toneAnalyzerChart = ChartFactory
						.createToneAnalyzerChartTone(toneAnalyzerResult);
			}
		} catch (Exception e) {
			Logging.logError(e);
		}
	}

	public String getToneAnalyzerEmotionToneChart() {
		return this.getToneAnalyzerChart(EMOTION_TONE);
	}

	public String getToneAnalyzerLanguageToneChart() {
		return this.getToneAnalyzerChart(LANGUAGE_TONE);
	}

	public String getToneAnalyzerSocialToneChart() {
		return this.getToneAnalyzerChart(SOCIAL_TONE);
	}

	public ToneAnalyzerResult getToneAnalyzerResult() {
		return toneAnalyzerResult;
	}

	public void setToneAnalyzerResult(ToneAnalyzerResult toneAnalyzerResult) {
		this.toneAnalyzerResult = toneAnalyzerResult;
	}

	private String getToneAnalyzerChart(String categoryId) {
		String result = "";

		try {
			if (toneAnalyzerChart != null) {
				result = mapper.writeValueAsString(this.toneAnalyzerChart
						.get(categoryId));
			}

		} catch (Exception e) {
			Logging.logError(e);
		}

		return result;
	}

}
