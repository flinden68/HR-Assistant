package ch.belsoft.charts.factory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;

import com.ibm.bluemix.services.watson.toneanalyzer.interfaces.ToneAnalyzable;
import com.ibm.bluemix.services.watson.toneanalyzer.model.Tone;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneAnalyzerResult;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneCategory;

import ch.belsoft.charts.model.Chart;
import ch.belsoft.charts.model.DataSet;

import ch.belsoft.tools.Logging;
import ch.belsoft.tools.Util;

public class ChartFactoryToneAnalyzer extends ChartFactory<ToneAnalyzable>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ToneCategoryEnum tone;

	public ChartFactoryToneAnalyzer(ToneCategoryEnum tone) {
		this.chartAlias = tone.toString();
		this.setDefaultChartType(this.chartAlias, ChartTypeSelection.RADAR.toString());
		this.tone = tone;
	}

	public static enum ToneCategoryEnum {
		EMOTION_TONE("emotion_tone"), LANGUAGE_TONE("language_tone"), SOCIAL_TONE(
				"social_tone");

		private final String tone;

		private ToneCategoryEnum(final String tone) {
			this.tone = tone;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return tone;
		}
	}

	protected ToneCategory getToneCategoryByTone(ToneCategoryEnum tone,
			ToneAnalyzerResult toneAnalyzerResult)
			throws NoSuchElementException {

		this.setDefaultChartType(this.chartAlias, ChartTypeSelection.RADAR);
		ToneCategory result = null;

		if (toneAnalyzerResult == null) {
			return null;
		}

		for (ToneCategory t : toneAnalyzerResult.getToneCategories()) {
			if (t.getCategory_id().equals(tone.toString())) {
				result = t;
				return result;
			}
		}

		throw new NoSuchElementException(tone.toString()
				+ " not found in the tone analyzer result");
	}

	protected void fillChartDataSets(Chart chart, ToneCategory toneCategory,
			String dataSetName, String dataSetLabelName) {

		try {

			DataSet dataSet = new DataSet();
			dataSet.setLabel(dataSetLabelName);
			dataSet.addBackgroundColor(Util.getRgbaColorOfString(dataSetName,
					opacity));
			for (Tone tone : toneCategory.getTones()) {
				chart.addLabel(tone.getName());
				dataSet.addData(tone.getScore());
			//	dataSet.setLabel(dataSetLabelName);
			}
			chart.addDataSet(dataSet);

		} catch (Exception e) {
			Logging.logError(e);
		}
	}

	@Override
	public Chart createChart(ToneAnalyzable toneAnalyzable) {
		Chart chart = new Chart(this.chartAlias);
		this.setDefaultChartType(this.chartAlias, ChartTypeSelection.RADAR);
		try {
			ToneCategory toneCat = this.getToneCategoryByTone(tone,
					toneAnalyzable.getToneAnalyzerResult());
			this.fillChartDataSets(chart, toneCat, toneAnalyzable.getName(),
					toneCat.getCategory_name());
		} catch (Exception e) {
			Logging.logError(e);
		}
		return chart;
	}

	@Override
	public Chart createChart(List<ToneAnalyzable> toneAnalyzableList) {
		Chart chart = new Chart(this.chartAlias);
		try {
			for (ToneAnalyzable toneAnalyzable : toneAnalyzableList) {
				ToneCategory toneCat = this.getToneCategoryByTone(tone,
						toneAnalyzable.getToneAnalyzerResult());
				this.fillChartDataSets(chart, toneCat,
						toneAnalyzable.getName(), toneAnalyzable.getName());
			}
		} catch (Exception e) {
			Logging.logError(e);
		}
		return chart;
	}
}
