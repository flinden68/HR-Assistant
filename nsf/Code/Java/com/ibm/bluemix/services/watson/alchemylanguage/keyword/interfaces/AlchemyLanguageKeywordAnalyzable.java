package com.ibm.bluemix.services.watson.alchemylanguage.keyword.interfaces;

import com.ibm.bluemix.services.watson.alchemylanguage.keyword.model.AlchemyLanguageKeywordResult;

public interface AlchemyLanguageKeywordAnalyzable {
    
    public String getName();
    
    public AlchemyLanguageKeywordResult getAlchemyLanguageResult();
    
    public void setAlchemyLanguageResult(AlchemyLanguageKeywordResult alchemyLanguageResult);
    
    public String getTextToAnalyze();
    
    public void setTextToAnalyze(String textToAnalyze);
}
