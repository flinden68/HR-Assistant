package com.ibm.bluemix.services.watson.personalityinsights.service;

import java.io.Serializable;

import com.ibm.bluemix.services.watson.personalityinsights.interfaces.IPersonalityInsightsService;
import com.ibm.bluemix.services.watson.personalityinsights.interfaces.PersonalityInsightable;
import com.ibm.bluemix.services.watson.personalityinsights.model.PersonalityInsightsRequest;
import com.ibm.bluemix.services.watson.personalityinsights.model.PersonalityInsightsResult;
import com.ibm.bluemix.services.watson.toneanalyzer.interfaces.ToneAnalyzable;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneAnalyzerRequest;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneAnalyzerResult;

import ch.belsoft.tools.Logging;

public class PersonalityInsightsConsumer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IPersonalityInsightsService personalityInsightsService;

	public PersonalityInsightsConsumer(final IPersonalityInsightsService svc) {
		this.personalityInsightsService = svc;
	}

	public void analyzePersonalityInsights(
			PersonalityInsightsRequest personalityInsightsRequest,
			PersonalityInsightable personalityInsightable) {
		try {
			PersonalityInsightsResult personalityInsightsResult = personalityInsightsService
					.analyzePersonalityInsights(personalityInsightsRequest);
			personalityInsightable
					.setPersonalityInsightsResult(personalityInsightsResult);
		} catch (Exception e) {
			Logging.logError(e);
		}
	}

}
