package com.ibm.bluemix.services.watson.alchemylanguage.injector;

import java.io.Serializable;

import org.apache.commons.lang3.StringEscapeUtils;

import ch.belsoft.tools.Logging;
import ch.belsoft.tools.XPagesUtil;

import com.ibm.bluemix.services.watson.alchemylanguage.interfaces.AlchemyLanguageAnalyzable;
import com.ibm.bluemix.services.watson.alchemylanguage.model.AlchemyLanguageRequest;
import com.ibm.bluemix.services.watson.alchemylanguage.service.AlchemyLanguageConsumer;
import com.ibm.bluemix.services.watson.alchemylanguage.service.AlchemyLanguageService;

public class AlchemyLanguageInjector implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private AlchemyLanguageService alchemyLanguageService = null;
    private AlchemyLanguageConsumer alchemyLanguageConsumer = null;
    
    private static final String BEAN_NAME = "alchemyLanguageInjector";
    
    public AlchemyLanguageInjector(){
        
    }
    
    // access to the bean
    public static AlchemyLanguageInjector get() {
        return (AlchemyLanguageInjector) XPagesUtil.resolveVariable(BEAN_NAME);
    }
    
    public void analyzeText(AlchemyLanguageAnalyzable alchemyLanguageAnalyzable) {
        try {
            AlchemyLanguageRequest alchemyLanguageRequest = new AlchemyLanguageRequest();
            String textToAnalyze = StringEscapeUtils
            .escapeHtml4(alchemyLanguageAnalyzable.getTextToAnalyze());
            alchemyLanguageRequest.setText(textToAnalyze);
            System.out.println("textToAnalyze="+textToAnalyze);
            getAlchemyLanguageConsumer().analyzeText(alchemyLanguageRequest, alchemyLanguageAnalyzable);
        } catch (Exception e) {
            Logging.logError(e);
        }
    }
    
    public AlchemyLanguageService getAlchemyLanguageService() {
        return alchemyLanguageService;
    }
    
    public void setAlchemyLanguageService(
            AlchemyLanguageService alchemyLanguageService) {
        this.alchemyLanguageService = alchemyLanguageService;
    }
    
    public AlchemyLanguageConsumer getAlchemyLanguageConsumer() {
        if (alchemyLanguageConsumer == null) {
            alchemyLanguageConsumer = new AlchemyLanguageConsumer(alchemyLanguageService);
        }
        return alchemyLanguageConsumer;
    }
    
    public void setAlchemyLanguageConsumer(
            AlchemyLanguageConsumer alchemyLanguageConsumer) {
        this.alchemyLanguageConsumer = alchemyLanguageConsumer;
    }
    
    
}
