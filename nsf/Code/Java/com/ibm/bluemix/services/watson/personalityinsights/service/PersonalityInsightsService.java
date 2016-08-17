package com.ibm.bluemix.services.watson.personalityinsights.service;

import ch.belsoft.tools.Logging;
import ch.belsoft.tools.XPagesUtil;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.ibm.bluemix.services.CloudService;
import com.ibm.bluemix.services.watson.personalityinsights.interfaces.IPersonalityInsightsService;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneAnalyzerRequest;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneAnalyzerResult;
import ch.belsoft.tools.RestUtil;

public class PersonalityInsightsService extends CloudService implements
		IPersonalityInsightsService {

	private static final String SERVICE_NAME = "Personality Insights-hr";
	private static final String API_URL = "https://gateway.watsonplatform.net/personality-insights/api/v2/profile";
	private static final String BEAN_NAME = "personalityInsightsService";

	// private boolean connected = false;

	private final ObjectMapper mapper = new ObjectMapper();

	public PersonalityInsightsService() {

	}

	// access to the bean
	public static PersonalityInsightsService get() {
		return (PersonalityInsightsService) XPagesUtil
				.resolveVariable(BEAN_NAME);
	}

	public String getServiceName() {
		return SERVICE_NAME;
	}

}
