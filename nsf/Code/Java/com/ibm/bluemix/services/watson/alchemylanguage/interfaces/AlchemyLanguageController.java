package com.ibm.bluemix.services.watson.alchemylanguage.interfaces;

import com.ibm.bluemix.services.watson.alchemylanguage.injector.AlchemyLanguageInjector;


public interface AlchemyLanguageController {
    
    public void analyzeTextAlchemyLanguage();
    
    public AlchemyLanguageInjector getAlchemyLanguageInjector();
    
    public void setAlchemyLanguageInjector(AlchemyLanguageInjector alchemyLanguageInjector);
    
}
