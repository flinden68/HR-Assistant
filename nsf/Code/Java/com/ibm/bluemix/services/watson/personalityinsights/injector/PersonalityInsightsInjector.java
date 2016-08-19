package com.ibm.bluemix.services.watson.personalityinsights.injector;

import java.io.Serializable;

import ch.belsoft.tools.XPagesUtil;

import com.ibm.bluemix.services.watson.personalityinsights.service.PersonalityInsightsService;
import com.ibm.bluemix.services.watson.toneanalyzer.service.ToneAnalyzerConsumer;

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
    
    public ToneAnalyzerConsumer getToneAnalyzerConsumer() {
        return toneAnalyzerConsumer;
    }
    
    public void setToneAnalyzerConsumer(ToneAnalyzerConsumer toneAnalyzerConsumer) {
        this.toneAnalyzerConsumer = toneAnalyzerConsumer;
    }
    
    public void setPersonalityInsightsService(PersonalityInsightsService personalityInsightsService) {
        this.personalityInsightsService = personalityInsightsService;
    }
    
    public PersonalityInsightsService getPersonalityInsightsService() {
        return personalityInsightsService;
    }
    
    // access to the bean
    public static PersonalityInsightsInjector get() {
        return (PersonalityInsightsInjector) XPagesUtil.resolveVariable(BEAN_NAME);
    }
    
    /*public ToneAnalyzerService getToneAnalyzerService() {
		return toneAnalyzerService;
	}

	public void setToneAnalyzerService(ToneAnalyzerService toneAnalyzerService) {
		this.toneAnalyzerService = toneAnalyzerService;
	}*/
    
}
