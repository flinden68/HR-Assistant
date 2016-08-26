package com.ibm.bluemix.services.watson.alchemylanguage.model;


public class AlchemyLanguageEntity {
    
    private String type = "";
    private String relevance = "";
    private int count = 0;
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
    
    @Override
    public String toString(){
        return "relevance : " + getRelevance() + " - text : " + getText();
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getType() {
        return type;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
    
    public int getCount() {
        return count;
    }
}
