package ch.belsoft.charts.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.ibm.bluemix.services.watson.toneanalyzer.interfaces.ToneAnalyzable;
import com.ibm.bluemix.services.watson.toneanalyzer.model.Tone;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneAnalyzerResult;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneCategory;

import ch.belsoft.charts.model.Chart;
import ch.belsoft.charts.model.DataSet;

import ch.belsoft.tools.Logging;
import ch.belsoft.tools.Util;

public class ChartFactoryToneAnalyzer extends ChartFactory implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String opacity = "0.5";

	private static final String EMOTION_TONE = "emotion_tone";
	private static final String LANGUAGE_TONE = "language_tone";
	private static final String SOCIAL_TONE = "social_tone";

	LinkedHashMap<String, Chart> toneAnalyzerChartsByTone = new LinkedHashMap<String, Chart>();

	public String getToneAnalyzerEmotionToneChart(
			List<ToneAnalyzable> toneAnalyzerableList) {
		return this.getToneAnalyzerChartFromList(EMOTION_TONE,
				toneAnalyzerableList);
	}

	public String getToneAnalyzerLanguageToneChart(
			List<ToneAnalyzable> toneAnalyzerableList) {
		return this.getToneAnalyzerChartFromList(LANGUAGE_TONE,
				toneAnalyzerableList);
	}

	public String getToneAnalyzerSocialToneChart(
			List<ToneAnalyzable> toneAnalyzerableList) {
		return this.getToneAnalyzerChartFromList(SOCIAL_TONE,
				toneAnalyzerableList);
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

	private String getToneAnalyzerChartFromList(String tone,
			List<ToneAnalyzable> toneAnalyzerableList) {
		if (this.toneAnalyzerChartsByTone.size() == 0) {
			this.createToneAnalyzerChartToneFromList(toneAnalyzerableList);
		}
		return getChartAsJson(this.toneAnalyzerChartsByTone.get(tone));
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
					dataSet.setBackgroundColor(Util.getRgbaColorOfString(
							toneAnalyzerable.getName(), opacity));
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

	private void createToneAnalyzerChartToneFromList(
			List<ToneAnalyzable> toneAnalyzerableList) {

		try {

			List<DataSet> dataSets = new ArrayList<DataSet>();

			for (ToneAnalyzable toneAnalyzerable : toneAnalyzerableList) {
				ToneAnalyzerResult toneAnalyzerResult = toneAnalyzerable
						.getToneAnalyzerResult();

				if (toneAnalyzerResult != null) {
					for (ToneCategory toneCategory : toneAnalyzerResult
							.getToneCategories()) {

						if (!this.toneAnalyzerChartsByTone
								.containsKey(toneCategory.getCategory_id())) {
							this.toneAnalyzerChartsByTone.put(toneCategory
									.getCategory_id(), new Chart());
						}

						Chart chart = this.toneAnalyzerChartsByTone
								.get(toneCategory.getCategory_id());

						DataSet dataSet = new DataSet();
						dataSet.setBackgroundColor(Util.getRgbaColorOfString(
								toneAnalyzerable.getName(), opacity));
						dataSet.setLabel(toneAnalyzerable.getName());
						for (Tone tone : toneCategory.getTones()) {
							chart.addLabel(tone.getName());
							dataSet.addData(tone.getScore());
							// dataSet.setLabel(toneCategory.getCategory_name());
						}
						chart.addDataSet(dataSet);
					}
				}
			}

		} catch (Exception e) {
			Logging.logError(e);
		}
	}

}
