package com.ibm.bluemix.services.watson.alchemylanguage.model;


public class AlchemyLanguageKeyword {
    
    private float relevance = 0;
    private String text = "";
    
    public AlchemyLanguageKeyword(){
    	
    }
    
    public AlchemyLanguageKeyword(String text, float relevance){
    	this.relevance = relevance;
    	this.text = text;
    }

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
    
    @Override
    public String toString(){
        return "relevance : " + getRelevance() + " - text : " + getText();
    }
    
    public int getPercentage(){
        return Math.round(relevance * 100);
    }
}
