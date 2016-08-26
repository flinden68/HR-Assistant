package com.ibm.bluemix.services.watson.alchemylanguage.model;

import java.util.Arrays;

public enum AlchemyLanguageExtractTypes {
    KEYWORDS("keywords"), TAXONOMY("taxonomy"), CONCEPTS("concepts");
    
    private final String extractType;
    
    /**
     * @param text
     */
    private AlchemyLanguageExtractTypes(final String extractType) {
        this.extractType = extractType;
    }
    
    @Override
    public String toString() {
        return extractType;
    }
    
    public static String[] extractTypeSelection() {
        AlchemyLanguageExtractTypes[] extractTypes = values();
        String[] selectionItem = new String[extractTypes.length];
        
        for (int i = 0; i < extractTypes.length; i++) {
            selectionItem[i] = extractTypes[i].toString();
        }
        
        Arrays.sort(selectionItem);
        return selectionItem;
    }
}
