package com.ibm.bluemix.services.watson.alchemylanguage.keyword.injector;

import java.io.Serializable;

import org.apache.commons.lang3.StringEscapeUtils;

import ch.belsoft.tools.Logging;
import ch.belsoft.tools.XPagesUtil;

import com.ibm.bluemix.services.watson.alchemylanguage.keyword.interfaces.AlchemyLanguageKeywordAnalyzable;
import com.ibm.bluemix.services.watson.alchemylanguage.keyword.model.AlchemyLanguageKeywordRequest;
import com.ibm.bluemix.services.watson.alchemylanguage.keyword.service.AlchemyLanguageKeywordConsumer;
import com.ibm.bluemix.services.watson.alchemylanguage.keyword.service.AlchemyLanguageKeywordService;

public class AlchemyLanguageKeywordInjector implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private AlchemyLanguageKeywordService alchemyLanguageKeywordService = null;
    private AlchemyLanguageKeywordConsumer alchemyLanguageKeywordConsumer = null;
    
    private static final String BEAN_NAME = "alchemyLanguageKeywordInjector";
    
    public AlchemyLanguageKeywordInjector(){
        
    }
    
    // access to the bean
    public static AlchemyLanguageKeywordInjector get() {
        return (AlchemyLanguageKeywordInjector) XPagesUtil.resolveVariable(BEAN_NAME);
    }
    
    public void analyzeText(AlchemyLanguageKeywordAnalyzable alchemyLanguageKeywordAnalyzable) {
        try {
            AlchemyLanguageKeywordRequest alchemyLanguageKeywordRequest = new AlchemyLanguageKeywordRequest();
            String textToAnalyze = StringEscapeUtils
            .escapeHtml4(alchemyLanguageKeywordAnalyzable.getTextToAnalyze());
            alchemyLanguageKeywordRequest.setText(textToAnalyze);
            getAlchemyLanguageKeywordConsumer().analyzeText(alchemyLanguageKeywordRequest, alchemyLanguageKeywordAnalyzable);
        } catch (Exception e) {
            Logging.logError(e);
        }
    }
    
    public AlchemyLanguageKeywordService getAlchemyLanguageKeywordService() {
        return alchemyLanguageKeywordService;
    }
    
    public void setAlchemyLanguageKeywordService(
            AlchemyLanguageKeywordService alchemyLanguageKeywordService) {
        this.alchemyLanguageKeywordService = alchemyLanguageKeywordService;
    }
    
    public AlchemyLanguageKeywordConsumer getAlchemyLanguageKeywordConsumer() {
        if (alchemyLanguageKeywordConsumer == null) {
            alchemyLanguageKeywordConsumer = new AlchemyLanguageKeywordConsumer(alchemyLanguageKeywordService);
        }
        return alchemyLanguageKeywordConsumer;
    }
    
    public void setAlchemyLanguageKeywordConsumer(
            AlchemyLanguageKeywordConsumer alchemyLanguageKeywordConsumer) {
        this.alchemyLanguageKeywordConsumer = alchemyLanguageKeywordConsumer;
    }
    
    
}
