package com.ibm.bluemix.services.watson.alchemylanguage.model;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ibm.bluemix.services.watson.alchemylanguage.comparator.AlchemyLanguageKeywordComparator;
import com.ibm.bluemix.services.watson.alchemylanguage.comparator.AlchemyLanguageTaxonomyComparator;

public class AlchemyLanguageResult {
    
    @JsonProperty("status")
    private String status = "";
    
    @JsonProperty("usage")
    private String usage = "";
    
    @JsonProperty("totalTransactions")
    private String totalTransactions = "";
    
    @JsonProperty("language")
    private String language = "";
    
    @JsonProperty("keywords")
    private List<AlchemyLanguageKeyword> keywords = null;
    
    @JsonProperty("concepts")
    private List<AlchemyLanguageConcept> concepts = null;
    
    @JsonProperty("entities")
    private List<AlchemyLanguageEntity> entities = null;
    
    @JsonProperty("taxonomy")
    private List<AlchemyLanguageTaxonomy> taxonomies = null;
    
    public void setKeywords(List<AlchemyLanguageKeyword> keywords) {
        this.keywords = keywords;
    }
    
    public List<AlchemyLanguageKeyword> getKeywords() {
        Collections.sort(keywords, new AlchemyLanguageKeywordComparator());
        return keywords;
    }
    
    public void setConcepts(List<AlchemyLanguageConcept> concepts) {
        this.concepts = concepts;
    }
    
    public List<AlchemyLanguageConcept> getConcepts() {
        return concepts;
    }
    
    public void setEntities(List<AlchemyLanguageEntity> entities) {
        this.entities = entities;
    }
    
    public List<AlchemyLanguageEntity> getEntities() {
        return entities;
    }
    
    public void setTaxonomies(List<AlchemyLanguageTaxonomy> taxonomies) {
        Collections.sort(taxonomies, new AlchemyLanguageTaxonomyComparator());
        this.taxonomies = taxonomies;
    }
    
    public List<AlchemyLanguageTaxonomy> getTaxonomies() {
        return taxonomies;
    }
    
    public String getStatus() {
        return status;
    }
    
    public String getUsage() {
        return usage;
    }
    
    public String getTotalTransactions() {
        return totalTransactions;
    }
    
    public String getLanguage() {
        return language;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setUsage(String usage) {
        this.usage = usage;
    }
    
    public void setTotalTransactions(String totalTransactions) {
        this.totalTransactions = totalTransactions;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("status : "+ getStatus()+ ",");
        sb.append("language : "+ getLanguage() + ",");
        sb.append("transactions : "+ getTotalTransactions() + ",");
        sb.append("keywords : "+ getKeywords());
        
        return sb.toString();
    }
    
}
