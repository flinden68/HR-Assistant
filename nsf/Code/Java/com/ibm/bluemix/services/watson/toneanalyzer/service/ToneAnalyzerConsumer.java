package com.ibm.bluemix.services.watson.toneanalyzer.service;

import java.io.Serializable;

import com.ibm.bluemix.services.watson.toneanalyzer.interfaces.ToneAnalyzable;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneAnalyzerRequest;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneAnalyzerResult;


import ch.belsoft.tools.Logging;

public class ToneAnalyzerConsumer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IToneAnalyzerService toneAnalyzerService;

	public ToneAnalyzerConsumer(final IToneAnalyzerService svc) {
		this.toneAnalyzerService = svc;
	}

	public void analyzeTone(ToneAnalyzerRequest toneAnalyzerRequest,
			ToneAnalyzable toneAnalyzable) {
		try {
			ToneAnalyzerResult toneAnalyzerResult = toneAnalyzerService
					.analyzeTone(toneAnalyzerRequest);
			toneAnalyzable.setToneAnalyzerResult(toneAnalyzerResult);
		} catch (Exception e) {
			Logging.logError(e);
		}
	}

}
