package com.ibm.bluemix.services.watson.alchemylanguage.interfaces;

import com.ibm.bluemix.services.watson.alchemylanguage.injector.AlchemyLanguageInjector;


public interface AlchemyLanguageAnalyzableController {
    
    public void analyzeText();
    
    public AlchemyLanguageInjector getAlchemyLanguageInjector();
    
    public void setAlchemyLanguageInjector(AlchemyLanguageInjector alchemyLanguageInjector);
    
}
