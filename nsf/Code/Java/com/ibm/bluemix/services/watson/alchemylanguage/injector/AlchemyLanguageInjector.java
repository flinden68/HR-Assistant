package com.ibm.bluemix.services.watson.alchemylanguage.injector;

import java.io.Serializable;

import org.apache.commons.lang3.StringEscapeUtils;

import ch.belsoft.tools.Logging;
import ch.belsoft.tools.XPagesUtil;

import com.ibm.bluemix.services.watson.alchemylanguage.interfaces.AlchemyLanguageAnalyzable;
import com.ibm.bluemix.services.watson.alchemylanguage.keyword.service.AlchemyLanguageConsumer;
import com.ibm.bluemix.services.watson.alchemylanguage.keyword.service.AlchemyLanguageService;
import com.ibm.bluemix.services.watson.alchemylanguage.model.AlchemyLanguageRequest;

public class AlchemyLanguageInjector implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private AlchemyLanguageService alchemyLanguageKeywordService = null;
    private AlchemyLanguageConsumer alchemyLanguageKeywordConsumer = null;
    
    private static final String BEAN_NAME = "alchemyLanguageKeywordInjector";
    
    public AlchemyLanguageInjector(){
        
    }
    
    // access to the bean
    public static AlchemyLanguageInjector get() {
        return (AlchemyLanguageInjector) XPagesUtil.resolveVariable(BEAN_NAME);
    }
    
    public void analyzeText(AlchemyLanguageAnalyzable alchemyLanguageKeywordAnalyzable) {
        try {
            AlchemyLanguageRequest alchemyLanguageKeywordRequest = new AlchemyLanguageRequest();
            String textToAnalyze = StringEscapeUtils
            .escapeHtml4(alchemyLanguageKeywordAnalyzable.getTextToAnalyze());
            alchemyLanguageKeywordRequest.setText(textToAnalyze);
            
            getAlchemyLanguageKeywordConsumer().analyzeText(alchemyLanguageKeywordRequest, alchemyLanguageKeywordAnalyzable);
        } catch (Exception e) {
            Logging.logError(e);
        }
    }
    
    public AlchemyLanguageService getAlchemyLanguageKeywordService() {
        return alchemyLanguageKeywordService;
    }
    
    public void setAlchemyLanguageKeywordService(
            AlchemyLanguageService alchemyLanguageKeywordService) {
        this.alchemyLanguageKeywordService = alchemyLanguageKeywordService;
    }
    
    public AlchemyLanguageConsumer getAlchemyLanguageKeywordConsumer() {
        if (alchemyLanguageKeywordConsumer == null) {
            alchemyLanguageKeywordConsumer = new AlchemyLanguageConsumer(alchemyLanguageKeywordService);
        }
        return alchemyLanguageKeywordConsumer;
    }
    
    public void setAlchemyLanguageKeywordConsumer(
            AlchemyLanguageConsumer alchemyLanguageKeywordConsumer) {
        this.alchemyLanguageKeywordConsumer = alchemyLanguageKeywordConsumer;
    }
    
    
}
