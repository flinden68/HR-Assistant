package com.ibm.bluemix.services.watson.toneanalyzer.interfaces;

import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneAnalyzerResult;

public interface ToneAnalyzable {

	public ToneAnalyzerResult getToneAnalyzerResult();

	public void setToneAnalyzerResult(ToneAnalyzerResult toneAnalyzerResult);
}
