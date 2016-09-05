package ch.belsoft.charts.factory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;

import com.ibm.bluemix.services.watson.personalityinsights.interfaces.PersonalityInsightable;
import com.ibm.bluemix.services.watson.personalityinsights.model.Child;
import com.ibm.bluemix.services.watson.personalityinsights.model.PersonalityInsightsResult;
import com.ibm.bluemix.services.watson.toneanalyzer.interfaces.ToneAnalyzable;
import com.ibm.bluemix.services.watson.toneanalyzer.model.Tone;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneAnalyzerResult;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneCategory;

import ch.belsoft.charts.model.Chart;
import ch.belsoft.charts.model.DataSet;

import ch.belsoft.tools.Logging;
import ch.belsoft.tools.Util;
import ch.belsoft.tools.XPagesUtil;

public class ChartFactoryPersonalityInsights extends
		ChartFactory<PersonalityInsightable> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PersonalityInsightsCategoryEnum personalityInsightsCategoryEnum;

	public ChartFactoryPersonalityInsights(
			PersonalityInsightsCategoryEnum personalityInsightsEnum) {
		this.chartAlias = personalityInsightsEnum.toString();
		this.setDefaultChartType(this.chartAlias, ChartTypeSelection.RADAR
				.toString());
		this.personalityInsightsCategoryEnum = personalityInsightsEnum;
	}

	public static enum PersonalityInsightsCategoryEnum {
		OPENNESS("Openness"), CONSCIENTIOUSNESS("Conscientiousness"), EXTRAVERSION(
				"Extraversion"), AGREEABLENESS("Agreeableness"), EMOTIONAL_RANGE(
				"Neuroticism"), SELF_EXPRESSION("Self-expression_parent"), HEDONISM(
				"Hedonism_parent");

		private final String personalityInsightsEnum;

		private PersonalityInsightsCategoryEnum(final String personality) {
			this.personalityInsightsEnum = personality;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return personalityInsightsEnum;
		}
	}

	private Child getInsightsChildByChildren(
			PersonalityInsightsCategoryEnum personalityCategory,
			Child childParam) {

		Child child = null;

		try {
			if (childParam != null) {
				for (Child c : childParam.getChildren()) {
					if (c.getId().equals(personalityCategory.toString())) {
						child = c;
						break;
					} else {
						if (c.hasChildren()) {
							child = getInsightsChildByChildren(
									personalityCategory, c);
							if (child != null) {
								break;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			Logging.logError(e);
		}
		return child;
	}

	protected Child getInsightsChildByPersonalityCategory(
			PersonalityInsightsCategoryEnum personalityCategory, Child tree) {

		Child result = null;
		try {
			if (tree == null) {
				return null;
			}

			result = this.getInsightsChildByChildren(personalityCategory, tree);
			if (result == null) {
			//	Logging.logEvent(personalityCategory.toString()
			//			+ " not found in the personality insights result");
			}
		} catch (Exception e) {
			Logging.logError(e);
		}

		return result;
	}

	protected void fillChartDataSets(Chart chart, Child child,
			String dataSetName, String dataSetLabelName) {

		try {

			DataSet dataSet = new DataSet();
			dataSet.setLabel(dataSetLabelName);
			dataSet.addBackgroundColor(Util.getRgbaColorOfString(dataSetName,
					opacity));
			for (Child c : child.getChildren()) {
				chart.addLabel(c.getName());
				dataSet.addData(c.getPercentage());
				// dataSet.setLabel(dataSetLabelName);
			}
			chart.addDataSet(dataSet);

		} catch (Exception e) {
			Logging.logError(e);
		}
	}

	@Override
	public Chart createChart(PersonalityInsightable personalityInsightable) {
		Chart chart = new Chart(this.chartAlias);
		this.setDefaultChartType(this.chartAlias, ChartTypeSelection.RADAR);
		try {
			Child child = null;
			if (personalityInsightable.getPersonalityInsightsResult() != null) {
				child = this.getInsightsChildByPersonalityCategory(
						this.personalityInsightsCategoryEnum,
						personalityInsightable.getPersonalityInsightsResult()
								.getTree());

			}
			if (child != null) {
				this.fillChartDataSets(chart, child, personalityInsightable
						.getName(), child.getName());
			}
		} catch (Exception e) {
			Logging.logError(e);
		}
		return chart;
	}

	@Override
	public Chart createChart(
			List<PersonalityInsightable> personalityInsightableList) {
		Chart chart = new Chart(this.chartAlias);
		this.setDefaultChartType(this.chartAlias, ChartTypeSelection.RADAR);
		try {
			for (PersonalityInsightable personalityInsightable : personalityInsightableList) {

				Child child = null;
				if (personalityInsightable.getPersonalityInsightsResult() != null) {
					child = this.getInsightsChildByPersonalityCategory(
							this.personalityInsightsCategoryEnum,
							personalityInsightable
									.getPersonalityInsightsResult().getTree());

				}
				if (child != null) {
					this.fillChartDataSets(chart, child, personalityInsightable
							.getName(), personalityInsightable.getName());
				}
			}
		} catch (Exception e) {
			Logging.logError(e);
		}
		return chart;
	}

	// @Override
	/*
	 * public Chart createChart(List<ToneAnalyzable> toneAnalyzableList) { Chart
	 * chart = new Chart(this.chartAlias); try { for (ToneAnalyzable
	 * toneAnalyzable : toneAnalyzableList) { ToneCategory toneCat =
	 * this.getToneCategoryByTone(tone, toneAnalyzable.getToneAnalyzerResult());
	 * this.fillChartDataSets(chart, toneCat, toneAnalyzable.getName(),
	 * toneAnalyzable.getName()); } } catch (Exception e) { Logging.logError(e);
	 * } return chart; }
	 */
}
