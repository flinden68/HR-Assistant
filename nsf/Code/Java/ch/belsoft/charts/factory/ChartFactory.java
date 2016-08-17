package ch.belsoft.charts.factory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.bluemix.services.watson.toneanalyzer.interfaces.ToneAnalyzable;
import com.ibm.bluemix.services.watson.toneanalyzer.model.Tone;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneAnalyzerResult;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneCategory;

import ch.belsoft.charts.factory.ChartFactoryJobApplications.ChartAlias;
import ch.belsoft.charts.model.Chart;
import ch.belsoft.charts.model.DataSet;
import ch.belsoft.hrassistant.config.model.ConfigDefault;
import ch.belsoft.hrassistant.model.DataItem;

import ch.belsoft.tools.Logging;
import ch.belsoft.tools.XPagesUtil;

public abstract class ChartFactory<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final ObjectMapper mapper = new ObjectMapper();
	protected static final String opacity = "0.5";

	protected String chartAlias;

	public void setDefaultChartType(String alias, String defaultChartType) {
	
		ChartTypeSelection chartTypeSelection = ChartTypeSelection.getBean();
		String chartType = chartTypeSelection.getChartType(alias);

		System.out.println("inside setDefaultChartType:"+alias+" defaultChartType:"+defaultChartType+" chart type="+chartType);
		
		if (chartType.equals("")) {
			System.out.println("setting the chart type: "+alias+" defaultChartType:"+defaultChartType);
			chartTypeSelection.setChartType(alias, defaultChartType);
		}
	}

	public static String getChartAsJson(Chart chart) {
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

	public abstract Chart createChart(T chartable);

	public abstract Chart createChart(List<T> chartable);

	public String getChartAlias() {
		return chartAlias;
	}

	public void setChartAlias(String chartAlias) {
		this.chartAlias = chartAlias;
	}

}
