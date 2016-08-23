package com.ibm.bluemix.services.watson.toneanalyzer.interfaces;

import com.ibm.bluemix.services.watson.toneanalyzer.injector.ToneAnalyzerInjector;


public interface ToneAnalyzableController {

	public void analyzeText();
 
	public ToneAnalyzerInjector getToneAnalyzerInjector();
	 
	public void setToneAnalyzerInjector(ToneAnalyzerInjector toneAnalyzerInjector);

}
