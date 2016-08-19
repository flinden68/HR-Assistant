package com.ibm.bluemix.services.watson.alchemylanguage.keyword.model;


public class AlchemyLanguageKeyword {
    
    private float relevance = 0;
    private String text = "";
    
    public void setRelevance(float relevance) {
        this.relevance = relevance;
    }
    
    public float getRelevance() {
        return relevance;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public String getText() {
        return text;
    }
}
