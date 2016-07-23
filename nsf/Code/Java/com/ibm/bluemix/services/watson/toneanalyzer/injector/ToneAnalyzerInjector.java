package com.ibm.bluemix.services.watson.toneanalyzer.injector;

import java.io.Serializable;

import org.apache.commons.lang3.StringEscapeUtils;

import com.ibm.bluemix.services.watson.toneanalyzer.interfaces.ToneAnalyzable;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneAnalyzerRequest;
import com.ibm.bluemix.services.watson.toneanalyzer.service.ToneAnalyzerConsumer;
import com.ibm.bluemix.services.watson.toneanalyzer.service.ToneAnalyzerService;

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

	public void analyzeTone(ToneAnalyzable toneAnalyzerable) {
		try {
			ToneAnalyzerRequest toneAnalyzerRequest = new ToneAnalyzerRequest();
			String textToAnalyze = StringEscapeUtils
					.escapeHtml4(toneAnalyzerable.getTextToAnalyze());
			toneAnalyzerRequest.setText(textToAnalyze);
			getToneAnalyzerConsumer().analyzeTone(toneAnalyzerRequest,
					toneAnalyzerable);
		} catch (Exception e) {
			Logging.logError(e);
		}
	}

	public ToneAnalyzerService getToneAnalyzerService() {
		return toneAnalyzerService;
	}

	public void setToneAnalyzerService(ToneAnalyzerService toneAnalyzerService) {
		this.toneAnalyzerService = toneAnalyzerService;
	}

}
