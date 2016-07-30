package ch.belsoft.charts.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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
import ch.belsoft.tools.XPagesUtil;

public abstract class ChartFactory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ObjectMapper mapper = new ObjectMapper();

	protected String getChartAsJson(Chart chart) {
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
