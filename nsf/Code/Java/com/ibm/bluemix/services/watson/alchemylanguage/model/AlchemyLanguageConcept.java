package com.ibm.bluemix.services.watson.alchemylanguage.model;


public class AlchemyLanguageConcept {
    
    private float relevance = 0;
    private String text = "";
    private String dbpedia = "";
    private String freebase = "";
    private String yago = "";
    private String opencyc = "";
    private String website = "";
    
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
    
    public void setDbpedia(String dbpedia) {
        this.dbpedia = dbpedia;
    }
    
    public String getDbpedia() {
        return dbpedia;
    }
    
    public void setFreebase(String freebase) {
        this.freebase = freebase;
    }
    
    public String getFreebase() {
        return freebase;
    }
    
    public void setYago(String yago) {
        this.yago = yago;
    }
    
    public String getYago() {
        return yago;
    }
    
    public String getOpencyc() {
        return opencyc;
    }
    
    public void setOpencyc(String opencyc) {
        this.opencyc = opencyc;
    }
    
    public String getWebsite() {
        return website;
    }
    
    public void setWebsite(String website) {
        this.website = website;
    }
}
