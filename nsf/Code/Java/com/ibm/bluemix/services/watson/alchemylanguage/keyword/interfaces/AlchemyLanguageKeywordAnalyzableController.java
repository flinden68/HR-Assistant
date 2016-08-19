package com.ibm.bluemix.services.watson.alchemylanguage.keyword.interfaces;

import com.ibm.bluemix.services.watson.toneanalyzer.injector.ToneAnalyzerInjector;


public interface AlchemyLanguageKeywordAnalyzableController {

	public void analyzeText();

	public ToneAnalyzerInjector getToneAnalyzerInjector();
	 
	public void setToneAnalyzerInjector(ToneAnalyzerInjector toneAnalyzerInjector);

}
