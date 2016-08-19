package com.ibm.bluemix.services.watson.alchemylanguage.keyword.interfaces;

import com.ibm.bluemix.services.ICloudService;
import com.ibm.bluemix.services.watson.alchemylanguage.keyword.model.AlchemyLanguageKeywordRequest;
import com.ibm.bluemix.services.watson.alchemylanguage.keyword.model.AlchemyLanguageKeywordResult;

public interface IAlchemyLanguageKeywordService extends ICloudService {
    
    public AlchemyLanguageKeywordResult analyzeText(AlchemyLanguageKeywordRequest request);
}
