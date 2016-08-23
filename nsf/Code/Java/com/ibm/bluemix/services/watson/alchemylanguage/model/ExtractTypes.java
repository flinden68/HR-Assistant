package com.ibm.bluemix.services.watson.alchemylanguage.model;

import java.util.Arrays;

public enum ExtractTypes {
    KEYWORDS("keywords"), ENTITIES("entities"), TAXONOMY("taxonomy");
    
    private final String extractType;
    
    /**
     * @param text
     */
    private ExtractTypes(final String extractType) {
        this.extractType = extractType;
    }
    
    @Override
    public String toString() {
        return extractType;
    }
    
    public static String[] extractTypeSelection() {
        ExtractTypes[] extractTypes = values();
        String[] selectionItem = new String[extractTypes.length];
        
        for (int i = 0; i < extractTypes.length; i++) {
            selectionItem[i] = extractTypes[i].toString();
        }
        
        Arrays.sort(selectionItem);
        return selectionItem;
    }
}
