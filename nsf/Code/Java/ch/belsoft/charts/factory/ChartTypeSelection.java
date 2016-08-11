package ch.belsoft.charts.factory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.bluemix.services.watson.toneanalyzer.interfaces.ToneAnalyzable;
import com.ibm.bluemix.services.watson.toneanalyzer.model.Tone;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneAnalyzerResult;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneCategory;

import ch.belsoft.charts.model.Chart;
import ch.belsoft.charts.model.DataSet;
import ch.belsoft.hrassistant.config.controller.ConfigurationController;
import ch.belsoft.hrassistant.config.model.ConfigDefault;
import ch.belsoft.hrassistant.model.DataItem;

import ch.belsoft.tools.Logging;
import ch.belsoft.tools.XPagesUtil;

public class ChartTypeSelection implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String RADAR = "radar";
	public static final String LINE = "line";
	public static final String BAR = "bar";
	public static final String POLAR = "polarArea";
	public static final String PIE = "pie";
	public static final String DOUGHNUT = "doughnut";

	private HashMap<String, String> chartTypeByChartAlias = new HashMap<String, String>();
	private static final String BEAN_NAME = "chartTypeSelection";

	public static ChartTypeSelection getBean() {
		return (ChartTypeSelection) XPagesUtil.resolveVariable(BEAN_NAME);
	}

	private List<String> chartTypeSelection;

	public ChartTypeSelection() {
		chartTypeSelection = new ArrayList<String>();
		chartTypeSelection.add("Radar Chart|" + RADAR);
		chartTypeSelection.add("Line Chart|" + LINE);
		chartTypeSelection.add("Bar Chart|" + BAR);
		chartTypeSelection.add("Polar Area Chart|" + POLAR);
		chartTypeSelection.add("Pie Chart|" + PIE);
		chartTypeSelection.add("doughnut Chart|" + DOUGHNUT);
	}

	public List<String> getChartTypeSelection() {
		return chartTypeSelection;
	}

	public void setChartTypeSelection(List<String> chartTypeSelection) {
		this.chartTypeSelection = chartTypeSelection;
	}

	public String getChartType(String alias) {
		if(chartTypeByChartAlias.containsKey(alias)){
			return chartTypeByChartAlias.get(alias);		
		}else{
			return "";
		}
	
	}

	public void setChartType(String alias, String chartType) {
		this.chartTypeByChartAlias.put(alias, chartType);
	}

}
