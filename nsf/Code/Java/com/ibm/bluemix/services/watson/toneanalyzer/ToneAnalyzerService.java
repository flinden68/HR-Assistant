package com.ibm.bluemix.services.watson.toneanalyzer;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import ch.belsoft.tools.Logging;
import ch.belsoft.tools.XPagesUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ibm.bluemix.services.CloudService;
import com.ibm.xsp.bluemix.util.BluemixContextUtil;
import ch.belsoft.tools.RestUtil;

public class ToneAnalyzerService extends CloudService {

	private static final String SERVICE_NAME = "tone_analyzer";
	private static final String API_URL = "https://gateway.watsonplatform.net/tone-analyzer/api";

	private boolean connected = false;

	private final ObjectMapper mapper = new ObjectMapper();

	public ToneAnalyzerService() {

	}

	public String getServiceName() {
		return SERVICE_NAME;
	}

	public void analizeText(String text) {
		try {
			ToneAnalyzerRequest req = new ToneAnalyzerRequest();
			req.setText(text);
			String postDataString = mapper.writeValueAsString(req);
		//	RestUtil.post(API_URL, bluemixUtil.getAuthorizationHeader(),
			//		postDataString);
		} catch (Exception e) {
			Logging.logError(e);
		}

	}
}
