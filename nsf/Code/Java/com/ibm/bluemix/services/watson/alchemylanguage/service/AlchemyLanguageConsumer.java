package com.ibm.bluemix.services.watson.alchemylanguage.service;

import java.io.Serializable;

import ch.belsoft.tools.Logging;

import com.ibm.bluemix.services.watson.alchemylanguage.interfaces.AlchemyLanguageAnalyzable;
import com.ibm.bluemix.services.watson.alchemylanguage.interfaces.IAlchemyLanguageService;
import com.ibm.bluemix.services.watson.alchemylanguage.model.AlchemyLanguageRequest;
import com.ibm.bluemix.services.watson.alchemylanguage.model.AlchemyLanguageResult;

public class AlchemyLanguageConsumer implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final IAlchemyLanguageService alchemyLanguageService;
    
    public AlchemyLanguageConsumer(final IAlchemyLanguageService svc){
        alchemyLanguageService = svc;
    }
    
    public void analyzeText(AlchemyLanguageRequest request,
            AlchemyLanguageAnalyzable alchemyLanguageAnalyzable) {
        try {
            AlchemyLanguageResult result = alchemyLanguageService.analyzeText(request);
            alchemyLanguageAnalyzable.setAlchemyLanguageResult(result);
        } catch (Exception e) {
            Logging.logError(e);
        }
    }
    
    
}
