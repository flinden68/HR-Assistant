package com.ibm.bluemix.services.watson.alchemylanguage.keyword.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AlchemyLanguageKeywordResult {
    
    @JsonProperty("keywords")
    private List<AlchemyLanguageKeyword> keywords = null;
    
    public void setKeywords(List<AlchemyLanguageKeyword> keywords) {
        this.keywords = keywords;
    }
    
    public List<AlchemyLanguageKeyword> getKeywords() {
        return keywords;
    }
}
