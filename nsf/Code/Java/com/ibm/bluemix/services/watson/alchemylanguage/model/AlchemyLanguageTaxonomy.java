package com.ibm.bluemix.services.watson.alchemylanguage.model;


public class AlchemyLanguageTaxonomy {
    
    private String score = "0";
    private String label = "";
    public void setScore(String score) {
        this.score = score;
    }
    public String getScore() {
        return score;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }
    
}
