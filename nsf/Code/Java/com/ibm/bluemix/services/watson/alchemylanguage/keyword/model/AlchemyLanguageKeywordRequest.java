package com.ibm.bluemix.services.watson.alchemylanguage.keyword.model;

public class AlchemyLanguageKeywordRequest {
    
    private String outputMode = "json";
    private String text = "";
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public void setOutputMode(String outputMode) {
        this.outputMode = outputMode;
    }
    
    public String getOutputMode() {
        return outputMode;
    }
    
}
