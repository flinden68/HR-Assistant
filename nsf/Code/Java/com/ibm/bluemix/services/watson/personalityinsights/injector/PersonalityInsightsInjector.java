package com.ibm.bluemix.services.watson.personalityinsights.injector;

import java.io.Serializable;

import org.apache.commons.lang3.StringEscapeUtils;

import com.ibm.bluemix.services.watson.toneanalyzer.interfaces.ToneAnalyzable;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneAnalyzerRequest;
import com.ibm.bluemix.services.watson.toneanalyzer.service.ToneAnalyzerConsumer;
import com.ibm.bluemix.services.watson.toneanalyzer.service.ToneAnalyzerService;

import ch.belsoft.tools.Logging;
import ch.belsoft.tools.XPagesUtil;

public class PersonalityInsightsInjector implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private PersonalityInsightsService personalityInsightsService = null;
	private ToneAnalyzerConsumer toneAnalyzerConsumer = null;

	private static final String BEAN_NAME = "personalityInsightsInjector";

	public PersonalityInsightsInjector() {

	}

	// access to the bean
	public static PersonalityInsightsInjector get() {
		return (PersonalityInsightsInjector) XPagesUtil.resolveVariable(BEAN_NAME);
	}

	public ToneAnalyzerService getToneAnalyzerService() {
		return toneAnalyzerService;
	}

	public void setToneAnalyzerService(ToneAnalyzerService toneAnalyzerService) {
		this.toneAnalyzerService = toneAnalyzerService;
	}

}
