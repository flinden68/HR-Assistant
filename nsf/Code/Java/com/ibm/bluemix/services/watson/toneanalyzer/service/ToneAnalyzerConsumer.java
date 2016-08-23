package com.ibm.bluemix.services.watson.toneanalyzer.service;

import java.io.Serializable;

import com.ibm.bluemix.services.watson.toneanalyzer.interfaces.IToneAnalyzerService;
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

	/**
	 * @param toneAnalyzerRequest
	 *            a created request object with content items
	 * @param toneAnalyzable
	 *            the result will be setted into this object
	 */
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
