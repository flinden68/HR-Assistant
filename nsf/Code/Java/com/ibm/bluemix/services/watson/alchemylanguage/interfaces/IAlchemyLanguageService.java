package com.ibm.bluemix.services.watson.alchemylanguage.interfaces;

import com.ibm.bluemix.services.ICloudService;
import com.ibm.bluemix.services.watson.alchemylanguage.model.AlchemyLanguageRequest;
import com.ibm.bluemix.services.watson.alchemylanguage.model.AlchemyLanguageResult;

public interface IAlchemyLanguageService extends ICloudService {
    
    public AlchemyLanguageResult analyzeText(AlchemyLanguageRequest request);
}
