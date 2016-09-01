package com.ibm.bluemix.services.watson.toneanalyzer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tone {
    
    private float score = 0;
    
    @JsonProperty("tone_id")
    private String id = "";
    
    @JsonProperty("tone_name")
    private String name = "";
    
    public float getScore() {
        return score;
    }
    
    public void setScore(float score) {
        this.score = score;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString(){
        return "Tone [id="+getId()+" - name="+getName()+" - score="+getScore()+"]";
    }
    
}
