package ch.belsoft.charts.controller;

import java.io.Serializable;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.bluemix.services.watson.toneanalyzer.interfaces.ToneAnalyzable;
import com.ibm.bluemix.services.watson.toneanalyzer.model.Tone;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneAnalyzerResult;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneCategory;

import ch.belsoft.charts.model.Chart;
import ch.belsoft.charts.model.DataSet;
import ch.belsoft.hrassistant.config.model.ConfigDefault;
import ch.belsoft.hrassistant.model.DataItem;

import ch.belsoft.tools.Logging;

public class ChartFactory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	LinkedHashMap<String, Chart> toneAnalyzerChartsByTone = new LinkedHashMap<String, Chart>();

	private static final String EMOTION_TONE = "emotion_tone";
	private static final String LANGUAGE_TONE = "language_tone";
	private static final String SOCIAL_TONE = "social_tone";
	private final ObjectMapper mapper = new ObjectMapper();

	public String getTest() {
		ConfigDefault config = new ConfigDefault();
		DataItem dateItem = config;
		return ((ConfigDefault) dateItem).getConfigKey();
	}

	public String getToneAnalyzerEmotionToneChart(
			ToneAnalyzable toneAnalyzerable) {
		return this.getToneAnalyzerChart(EMOTION_TONE, toneAnalyzerable);
	}

	public String getToneAnalyzerLanguageToneChart(
			ToneAnalyzable toneAnalyzerable) {
		return this.getToneAnalyzerChart(LANGUAGE_TONE, toneAnalyzerable);
	}

	public String getToneAnalyzerSocialToneChart(ToneAnalyzable toneAnalyzerable) {
		return this.getToneAnalyzerChart(SOCIAL_TONE, toneAnalyzerable);
	}

	private String getToneAnalyzerChart(String tone,
			ToneAnalyzable toneAnalyzerable) {
		if (this.toneAnalyzerChartsByTone.size() == 0) {
			this.createToneAnalyzerChartTone(toneAnalyzerable);
		}
		return getChartAsJson(this.toneAnalyzerChartsByTone.get(tone));
	}

	private void createToneAnalyzerChartTone(ToneAnalyzable toneAnalyzerable) {

		try {

			ToneAnalyzerResult toneAnalyzerResult = toneAnalyzerable
					.getToneAnalyzerResult();

			if (toneAnalyzerResult != null) {
				for (ToneCategory toneCategory : toneAnalyzerResult
						.getToneCategories()) {
					Chart chart = new Chart();
					DataSet dataSet = new DataSet();
					dataSet.setLabel(toneCategory.getCategory_name());
					for (Tone tone : toneCategory.getTones()) {
						chart.addLabel(tone.getName());
						dataSet.addData(tone.getScore());
						dataSet.setLabel(toneCategory.getCategory_name());
					}
					chart.addDataSet(dataSet);
					toneAnalyzerChartsByTone.put(toneCategory.getCategory_id(),
							chart);
				}
			}
		} catch (Exception e) {
			Logging.logError(e);
		}
	}

	private String getChartAsJson(Chart chart) {
		String result = "";

		try {
			if (chart != null) {
				result = mapper.writeValueAsString(chart);
			}

		} catch (Exception e) {
			Logging.logError(e);
		}

		return result;
	}
}
