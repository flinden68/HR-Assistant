package com.ibm.bluemix.services.watson.alchemylanguage.keyword.service;

import java.io.Serializable;

import ch.belsoft.tools.Logging;

import com.ibm.bluemix.services.watson.alchemylanguage.keyword.interfaces.AlchemyLanguageKeywordAnalyzable;
import com.ibm.bluemix.services.watson.alchemylanguage.keyword.interfaces.IAlchemyLanguageKeywordService;
import com.ibm.bluemix.services.watson.alchemylanguage.keyword.model.AlchemyLanguageKeywordRequest;
import com.ibm.bluemix.services.watson.alchemylanguage.keyword.model.AlchemyLanguageKeywordResult;

public class AlchemyLanguageKeywordConsumer implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final IAlchemyLanguageKeywordService alchemyLanguageService;
    
    public AlchemyLanguageKeywordConsumer(final IAlchemyLanguageKeywordService svc){
        alchemyLanguageService = svc;
    }
    
    public void analyzeText(AlchemyLanguageKeywordRequest request,
            AlchemyLanguageKeywordAnalyzable alchemyLanguageAnalyzable) {
        try {
            AlchemyLanguageKeywordResult result = alchemyLanguageService.analyzeText(request);
            alchemyLanguageAnalyzable.setAlchemyLanguageResult(result);
        } catch (Exception e) {
            Logging.logError(e);
        }
    }
    
    
}
