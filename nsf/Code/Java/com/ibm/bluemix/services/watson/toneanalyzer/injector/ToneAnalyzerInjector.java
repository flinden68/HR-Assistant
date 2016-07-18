package com.ibm.bluemix.services.watson.toneanalyzer.injector;

import java.io.Serializable;

import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneAnalyzerRequest;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneAnalyzerResult;
import com.ibm.bluemix.services.watson.toneanalyzer.service.ToneAnalyzerConsumer;
import com.ibm.bluemix.services.watson.toneanalyzer.service.ToneAnalyzerService;

import ch.belsoft.charts.model.Chart;
import ch.belsoft.hrassistant.controller.ControllerBase;
import ch.belsoft.hrassistant.controller.IGuiController;
import ch.belsoft.hrassistant.service.CloudantService;
import ch.belsoft.tools.Logging;
import ch.belsoft.tools.XPagesUtil;

public class ToneAnalyzerInjector implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private ToneAnalyzerService toneAnalyzerService = null;
	private ToneAnalyzerConsumer toneAnalyzerConsumer = null;

	private static final String BEAN_NAME = "toneAnalyzerInjector";

	public ToneAnalyzerInjector() {

	}

	// access to the bean
	public static ToneAnalyzerInjector get() {
		return (ToneAnalyzerInjector) XPagesUtil.resolveVariable(BEAN_NAME);
	}

	private ToneAnalyzerConsumer getToneAnalyzerConsumer() {
		if (toneAnalyzerConsumer == null) {
			toneAnalyzerConsumer = new ToneAnalyzerConsumer(toneAnalyzerService);
		}
		return toneAnalyzerConsumer;
	}

	public void analyzeTone(String text) {
		try {
			ToneAnalyzerRequest toneAnalyzerRequest = new ToneAnalyzerRequest();
			toneAnalyzerRequest.setText(text);
			getToneAnalyzerConsumer().analyzeTone(toneAnalyzerRequest);
		} catch (Exception e) {
			Logging.logError(e);
		}
	}

	public ToneAnalyzerResult getToneAnalyzerResult() {
		return getToneAnalyzerConsumer().getToneAnalyzerResult();
	}

	public ToneAnalyzerService getToneAnalyzerService() {
		return toneAnalyzerService;
	}

	public void setToneAnalyzerService(ToneAnalyzerService toneAnalyzerService) {
		this.toneAnalyzerService = toneAnalyzerService;
	}

	public String getToneAnalyzerEmotionToneChart() {
		return getToneAnalyzerConsumer().getToneAnalyzerEmotionToneChart();
	}

	public String getToneAnalyzerLanguageToneChart() {
		return getToneAnalyzerConsumer().getToneAnalyzerLanguageToneChart();
	}

	public String getToneAnalyzerSocialToneChart() {
		return getToneAnalyzerConsumer().getToneAnalyzerSocialToneChart();
	}

}
