package com.ibm.bluemix.services.watson.alchemylanguage.model;


public class AlchemyLanguageConcept {
    
    private String relevance = "";
    private String text = "";
    private String dbpedia = "";
    private String freebase = "";
    private String yago = "";
    
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
}
