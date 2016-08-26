package com.ibm.bluemix.services.watson.alchemylanguage.model;


public class AlchemyLanguageChartCategory {
    
    private float score = 0;
    private String name = "";
    
    
    @Override
    public String toString(){
        return "score : " + getScore() + " - name : " + getName();
    }
    
    
    public void setScore(float score) {
        this.score = score;
    }
    
    
    public float getScore() {
        return score;
    }
    
    
    public void setName(String name) {
        this.name = name;
    }
    
    
    public String getName() {
        return name;
    }
}
