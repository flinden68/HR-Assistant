package com.ibm.bluemix.services.watson.alchemylanguage.model;


public class AlchemyLanguageTaxonomy {
    
    private float score = 0;
    private String label = "";
    private String confident = "";
    
    public void setScore(float score) {
        this.score = score;
    }
    public float getScore() {
        return score;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }
    
    @Override
    public String toString(){
        return "score : " + getScore() + " - label : " + getLabel();
    }
    
    public int getPercentage(){
        return Math.round(score * 100);
    }
    public void setConfident(String confident) {
        this.confident = confident;
    }
    public String getConfident() {
        return confident;
    }
}
