package com.ibm.bluemix.services.watson.toneanalyzer.service;
import ch.belsoft.tools.Logging;
import ch.belsoft.tools.XPagesUtil;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.ibm.bluemix.services.CloudService;
import com.ibm.bluemix.services.watson.toneanalyzer.interfaces.IToneAnalyzerService;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneAnalyzerRequest;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneAnalyzerResult;
import ch.belsoft.tools.RestUtil;

public class ToneAnalyzerService extends CloudService implements
		IToneAnalyzerService {

	private static final String SERVICE_NAME = "tone_analyzer";
	private static final String API_URL = "https://gateway.watsonplatform.net/tone-analyzer/api/v3/tone?version=2016-05-19&sentences=false";
	private static final String BEAN_NAME = "toneAnalyzerService";

	// private boolean connected = false;

	private final ObjectMapper mapper = new ObjectMapper();

	public ToneAnalyzerService() {

	}

	// access to the bean
	public static ToneAnalyzerService get() {
		return (ToneAnalyzerService) XPagesUtil.resolveVariable(BEAN_NAME);
	}

	public String getServiceName() {
		return SERVICE_NAME;
	}

	public ToneAnalyzerResult analyzeTone(ToneAnalyzerRequest req) {
		ToneAnalyzerResult result = null;
		try {
			super.connect();
			String postDataString = mapper.writeValueAsString(req);
			String response = RestUtil.post(API_URL, bluemixUtil
					.getAuthorizationHeader(), postDataString);
			mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
			result = mapper.readValue(response, ToneAnalyzerResult.class);
		} catch (Exception e) {
			Logging.logError(e);
		}
		return result;
	}
}
