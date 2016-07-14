package ch.belsoft.charts.controller;

import java.io.Serializable;
import java.util.LinkedHashMap;

import com.ibm.bluemix.services.watson.toneanalyzer.model.Tone;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneAnalyzerResult;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneCategory;

import ch.belsoft.charts.model.Chart;
import ch.belsoft.charts.model.DataSet;

import ch.belsoft.tools.Logging;

public class ChartFactory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static LinkedHashMap<String, Chart> createToneAnalyzerChartTone(
			ToneAnalyzerResult toneAnalyzerResult) {

		LinkedHashMap<String, Chart> result = new LinkedHashMap<String, Chart>();

		try {
			for (ToneCategory toneCategory : toneAnalyzerResult
					.getToneCategories()) {
				// result.addLabel(toneCategory.getCategory_name());
				Chart chart = new Chart();
				DataSet dataSet = new DataSet();
				dataSet.setLabel(toneCategory.getCategory_name());
				for (Tone tone : toneCategory.getTones()) {
					chart.addLabel(tone.getName());
					dataSet.addData(tone.getScore());
					dataSet.setLabel(toneCategory.getCategory_name());
				}
				chart.addDataSet(dataSet);
				result.put(toneCategory.getCategory_id(), chart);
			}
		} catch (Exception e) {
			Logging.logError(e);
		}

		return result;
	}
}
