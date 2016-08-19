package com.ibm.bluemix.services.watson.alchemylanguage.keyword.service;

import ch.belsoft.tools.Logging;
import ch.belsoft.tools.RestUtil;
import ch.belsoft.tools.XPagesUtil;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.bluemix.services.CloudService;
import com.ibm.bluemix.services.watson.alchemylanguage.keyword.interfaces.IAlchemyLanguageKeywordService;
import com.ibm.bluemix.services.watson.alchemylanguage.keyword.model.AlchemyLanguageKeywordRequest;
import com.ibm.bluemix.services.watson.alchemylanguage.keyword.model.AlchemyLanguageKeywordResult;

public class AlchemyLanguageKeywordService extends CloudService implements IAlchemyLanguageKeywordService {
    
    private static final String SERVICE_NAME = "AlchemyAPI-y2";
    private static final String API_URL = "https://gateway.watsonplatform.net/tone-analyzer/api/v3/tone?version=2016-05-19&sentences=false";
    private static final String BEAN_NAME = "alchemyLanguageService";
    
    private final ObjectMapper mapper = new ObjectMapper();
    
    public AlchemyLanguageKeywordService(){
        
    }
    // access to the bean
    public static AlchemyLanguageKeywordService get() {
        return (AlchemyLanguageKeywordService) XPagesUtil.resolveVariable(BEAN_NAME);
    }
    
    public AlchemyLanguageKeywordResult analyzeText(AlchemyLanguageKeywordRequest request) {
        AlchemyLanguageKeywordResult result = null;
        try {
            super.connect();
            String postDataString = mapper.writeValueAsString(request);
            String response = RestUtil.post(API_URL, bluemixUtil
                    .getAuthorizationHeader(), postDataString);
            XPagesUtil.showErrorMessage(response);
            mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
            result = mapper.readValue(response, AlchemyLanguageKeywordResult.class);
        } catch (Exception e) {
            Logging.logError(e);
        }
        return result;
    }
    
    @Override
    protected String getServiceName() {
        return SERVICE_NAME;
    }
    
}
