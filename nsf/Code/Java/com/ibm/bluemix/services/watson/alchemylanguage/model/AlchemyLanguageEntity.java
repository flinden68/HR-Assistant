package com.ibm.bluemix.services.watson.alchemylanguage.model;


public class AlchemyLanguageEntity {
    
    private String relevance = "";
    private String text = "";
    private int count = 0;
    private String type = "";
    
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
    
    public void setCount(int count) {
        this.count = count;
    }
    
    public int getCount() {
        return count;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getType() {
        return type;
    }
    
}
