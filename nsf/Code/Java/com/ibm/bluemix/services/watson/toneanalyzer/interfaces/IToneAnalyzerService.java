package com.ibm.bluemix.services.watson.toneanalyzer.interfaces;

import com.ibm.bluemix.services.ICloudService;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneAnalyzerRequest;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneAnalyzerResult;

public interface IToneAnalyzerService extends ICloudService {
	public ToneAnalyzerResult analyzeTone(ToneAnalyzerRequest req);
}
