package com.ibm.bluemix.services.watson.alchemylanguage.comparator;

import java.io.Serializable;
import java.util.Comparator;

import com.ibm.bluemix.services.watson.alchemylanguage.model.AlchemyLanguageKeyword;

public class AlchemyLanguageKeywordComparator implements Comparator<AlchemyLanguageKeyword>,Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public int compare(AlchemyLanguageKeyword t1, AlchemyLanguageKeyword t2) {
        float f1 = t1.getRelevance();
        float f2 = t2.getRelevance();
        return Float.compare(f2, f1);
    }
    
}
