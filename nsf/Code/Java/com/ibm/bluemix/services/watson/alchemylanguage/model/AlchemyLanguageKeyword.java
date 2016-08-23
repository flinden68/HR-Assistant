package com.ibm.bluemix.services.watson.alchemylanguage.model;


public class AlchemyLanguageKeyword {
    
    private String relevance = "";
    private String text = "";
    
    public void setRelevance(String relevance) {
        this.relevance = relevance;
    }
    
    public String getRelevance() {
        return relevance;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public String getText() {
        return text;
    }
}
