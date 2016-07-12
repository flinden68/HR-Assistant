package ch.belsoft.hrassistant.watson.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.bluemix.services.watson.toneanalyzer.ToneAnalyzerRequest;
import com.ibm.bluemix.services.watson.toneanalyzer.ToneAnalyzerResult;
import com.ibm.bluemix.services.watson.toneanalyzer.ToneAnalyzerService;

import ch.belsoft.charts.controller.ChartFactory;
import ch.belsoft.charts.model.Chart;
import ch.belsoft.hrassistant.config.dao.ConfigurationDAO;
import ch.belsoft.hrassistant.config.model.ConfigDefault;
import ch.belsoft.hrassistant.config.model.ConfigType;
import ch.belsoft.hrassistant.config.model.IConfiguration;
import ch.belsoft.hrassistant.controller.ControllerBase;
import ch.belsoft.hrassistant.controller.IGuiController;
import ch.belsoft.tools.Logging;
import ch.belsoft.tools.XPagesUtil;
import ch.belsoft.tools.fa.FontAwesomeIcons;

public class WatsonAPITestController extends ControllerBase implements
		IGuiController<ToneAnalyzerRequest>, Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private static final String PAGETITLE = "Watson API Test";

	private ToneAnalyzerRequest toneAnalyzerRequest = new ToneAnalyzerRequest();
	private ToneAnalyzerResult toneAnalyzerResult = null;
	private ToneAnalyzerService toneAnalyzerService = null;
	private LinkedHashMap<String, Chart> toneAnalyzerChart = null;
	private final ObjectMapper mapper = new ObjectMapper();
	private static final String EMOTION_TONE = "emotion_tone";

	public WatsonAPITestController() {

	}

	public ToneAnalyzerRequest getDataContext() {
		return toneAnalyzerRequest;
	}

	public String getPageTitle() {
		return PAGETITLE;
	}

	public void analizeText() {
		try {
			this.toneAnalyzerResult = toneAnalyzerService
					.analyzeTone(toneAnalyzerRequest);
			this.toneAnalyzerChart = ChartFactory
					.createToneAnalyzerChartTone(toneAnalyzerResult);
		} catch (Exception e) {
			Logging.logError(e);
		}
	}

	public String getToneAnalyzerChart() {

		String result = "";

		try {
			if (toneAnalyzerChart != null) {
				result = mapper.writeValueAsString(this.toneAnalyzerChart
						.get(EMOTION_TONE));
			} else {
				result = "Could not get a chart json..";
			}

		} catch (Exception e) {
			Logging.logError(e);
		}

		return result;
	}

	public ToneAnalyzerRequest getToneAnalyzerRequest() {
		return toneAnalyzerRequest;
	}

	public void setToneAnalyzerRequest(ToneAnalyzerRequest toneAnalyzerRequest) {
		this.toneAnalyzerRequest = toneAnalyzerRequest;
	}

	public ToneAnalyzerResult getToneAnalyzerResult() {
		return toneAnalyzerResult;
	}

	public void setToneAnalyzerResult(ToneAnalyzerResult toneAnalyzerResult) {
		this.toneAnalyzerResult = toneAnalyzerResult;
	}

	public ToneAnalyzerService getToneAnalyzerService() {
		return toneAnalyzerService;
	}

	public void setToneAnalyzerService(ToneAnalyzerService toneAnalyzerService) {
		this.toneAnalyzerService = toneAnalyzerService;
	}

}
