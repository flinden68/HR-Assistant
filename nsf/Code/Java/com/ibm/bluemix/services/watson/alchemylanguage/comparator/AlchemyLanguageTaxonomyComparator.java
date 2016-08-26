package com.ibm.bluemix.services.watson.alchemylanguage.comparator;

import java.io.Serializable;
import java.util.Comparator;

import com.ibm.bluemix.services.watson.alchemylanguage.model.AlchemyLanguageTaxonomy;

public class AlchemyLanguageTaxonomyComparator implements Comparator<AlchemyLanguageTaxonomy>,Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public int compare(AlchemyLanguageTaxonomy t1, AlchemyLanguageTaxonomy t2) {
        float f1 = t1.getScore();
        float f2 = t2.getScore();
        return Float.compare(f2, f1);
    }
    
}
