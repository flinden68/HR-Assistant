package com.ibm.bluemix.services.watson.alchemylanguage.model;

public class AlchemyLanguageRequest {
    
    private String apikey = "";
    private String outputMode = "json";
    private String text = "";
    private String extract = "";
    
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
    
    public void setApikey(String apikey) {
        this.apikey = apikey;
    }
    
    public String getApikey() {
        return apikey;
    }
    
    public void setExtract(String extract) {
        this.extract = extract;
    }
    
    public String getExtract() {
        return extract;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("apikey : "+ getApikey() + ",");
        sb.append("outputMode : "+ getOutputMode() + ",");
        sb.append("extract : "+ getExtract() + ",");
        sb.append("text : "+ getText());
        
        return sb.toString();
    }
    
    public String toQueryString(){
        StringBuilder sb = new StringBuilder();
        sb.append("apikey="+ getApikey() + "&");
        sb.append("outputMode="+ getOutputMode() + "&");
        sb.append("extract="+ getExtract() + "&");
        sb.append("text="+ getText());
        return sb.toString();
    }
    
}
