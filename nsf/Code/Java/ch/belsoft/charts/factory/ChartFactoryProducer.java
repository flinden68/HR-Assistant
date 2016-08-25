package ch.belsoft.charts.factory;

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

public class ChartFactoryProducer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ChartFactoryToneAnalyzer getChartFactoryToneAnalyzerEmotionTone() {
		return new ChartFactoryToneAnalyzer(
				ChartFactoryToneAnalyzer.ToneCategoryEnum.EMOTION_TONE);
	}

	public ChartFactoryToneAnalyzer getChartFactoryToneAnalyzerLanguageTone() {
		return new ChartFactoryToneAnalyzer(
				ChartFactoryToneAnalyzer.ToneCategoryEnum.LANGUAGE_TONE);
	}

	public ChartFactoryToneAnalyzer getChartFactoryToneAnalyzerSocialTone() {
		return new ChartFactoryToneAnalyzer(
				ChartFactoryToneAnalyzer.ToneCategoryEnum.SOCIAL_TONE);
	}

	public ChartFactoryJobApplications getChartFactoryApplicationsByJob() {
		return new ChartFactoryJobApplications(
				ChartFactoryJobApplications.ChartAlias.JOB_APPLICATIONS_BY_JOB);
	}

	/**
	 * @return OPENNESS Chart
	 */
	public ChartFactoryPersonalityInsights getChartFactoryPersonalityInsightsOpenness() {
		return this
				.getChartFactoryPersonalityInsights(ChartFactoryPersonalityInsights.PersonalityInsightsCategoryEnum.OPENNESS);
	}

	/**
	 * @return CONSCIENTIOUSNESS Chart
	 */
	public ChartFactoryPersonalityInsights getChartFactoryPersonalityInsightsConscientiousness() {
		return this
				.getChartFactoryPersonalityInsights(ChartFactoryPersonalityInsights.PersonalityInsightsCategoryEnum.CONSCIENTIOUSNESS);
	}

	/**
	 * @return AGREEABLENESS Chart
	 */
	public ChartFactoryPersonalityInsights getChartFactoryPersonalityInsightsAgreeableness() {
		return this
				.getChartFactoryPersonalityInsights(ChartFactoryPersonalityInsights.PersonalityInsightsCategoryEnum.AGREEABLENESS);
	}

	/**
	 * @return EMOTIONAL_RANGE Chart
	 */
	public ChartFactoryPersonalityInsights getChartFactoryPersonalityInsightsEmotionalRange() {
		return this
				.getChartFactoryPersonalityInsights(ChartFactoryPersonalityInsights.PersonalityInsightsCategoryEnum.EMOTIONAL_RANGE);
	}

	/**
	 * @return EXTRAVERSION Chart
	 */
	public ChartFactoryPersonalityInsights getChartFactoryPersonalityInsightsExtraversion() {
		return this
				.getChartFactoryPersonalityInsights(ChartFactoryPersonalityInsights.PersonalityInsightsCategoryEnum.EXTRAVERSION);
	}

	/**
	 * @return HEDONISM Chart
	 */
	public ChartFactoryPersonalityInsights getChartFactoryPersonalityInsightsHedonism() {
		return this
				.getChartFactoryPersonalityInsights(ChartFactoryPersonalityInsights.PersonalityInsightsCategoryEnum.HEDONISM);
	}

	/**
	 * @return SELF_EXPRESSION Chart
	 */
	public ChartFactoryPersonalityInsights getChartFactoryPersonalityInsightsSelfExpression() {
		return this
				.getChartFactoryPersonalityInsights(ChartFactoryPersonalityInsights.PersonalityInsightsCategoryEnum.SELF_EXPRESSION);
	}

	private ChartFactoryPersonalityInsights getChartFactoryPersonalityInsights(
			ChartFactoryPersonalityInsights.PersonalityInsightsCategoryEnum cat) {

		return new ChartFactoryPersonalityInsights(cat);

	}

}
