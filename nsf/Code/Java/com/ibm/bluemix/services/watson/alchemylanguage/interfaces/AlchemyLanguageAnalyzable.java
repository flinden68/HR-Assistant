package com.ibm.bluemix.services.watson.alchemylanguage.interfaces;

import com.ibm.bluemix.services.watson.alchemylanguage.model.AlchemyLanguageResult;

public interface AlchemyLanguageAnalyzable {
    
    public String getName();
    
    public AlchemyLanguageResult getAlchemyLanguageResult();
    
    public void setAlchemyLanguageResult(AlchemyLanguageResult alchemyLanguageResult);
    
    public String getTextToAnalyze();
    
    public void setTextToAnalyze(String textToAnalyze);
}
