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
		this.tone = tone;
	}

	public static enum ToneCategoryEnum {
		EMOTION_TONE("emotion_tone"), LANGUAGE_TONE("language_tone"), SOCIAL_TONE(
				"social_tone");

		private final String tone;

		/**
		 * @param text
		 */
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
			String dataSetName) {

		try {

			DataSet dataSet = new DataSet();
			dataSet.setLabel(toneCategory.getCategory_name());
			dataSet.addBackgroundColor(Util.getRgbaColorOfString(dataSetName,
					opacity));
			for (Tone tone : toneCategory.getTones()) {
				chart.addLabel(tone.getName());
				dataSet.addData(tone.getScore());
				dataSet.setLabel(toneCategory.getCategory_name());
			}
			chart.addDataSet(dataSet);

		} catch (Exception e) {
			Logging.logError(e);
		}
	}

	@Override
	public Chart createChart(ToneAnalyzable toneAnalyzable) {
		Chart chart = new Chart();
		try {
			ToneCategory toneCat = this.getToneCategoryByTone(tone,
					toneAnalyzable.getToneAnalyzerResult());
			this.fillChartDataSets(chart, toneCat, toneAnalyzable.getName());
		} catch (Exception e) {
			Logging.logError(e);
		}
		return chart;
	}

	@Override
	public Chart createChart(List<ToneAnalyzable> toneAnalyzableList) {
		Chart chart = new Chart();
		try {
			for (ToneAnalyzable toneAnalyzable : toneAnalyzableList) {
				ToneCategory toneCat = this.getToneCategoryByTone(tone,
						toneAnalyzable.getToneAnalyzerResult());
				this
						.fillChartDataSets(chart, toneCat, toneAnalyzable
								.getName());
			}
		} catch (Exception e) {
			Logging.logError(e);
		}
		return chart;
	}
}
