package com.ibm.bluemix.services.watson.alchemylanguage.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AlchemyLanguageResult {
    
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
        this.taxonomies = taxonomies;
    }
    
    public List<AlchemyLanguageTaxonomy> getTaxonomies() {
        return taxonomies;
    }
}
