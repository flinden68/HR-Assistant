package com.ibm.bluemix.services.watson.alchemylanguage.service;

import ch.belsoft.tools.Logging;
import ch.belsoft.tools.RestUtil;
import ch.belsoft.tools.XPagesUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.bluemix.services.CloudService;
import com.ibm.bluemix.services.watson.alchemylanguage.interfaces.IAlchemyLanguageService;
import com.ibm.bluemix.services.watson.alchemylanguage.model.AlchemyLanguageRequest;
import com.ibm.bluemix.services.watson.alchemylanguage.model.AlchemyLanguageResult;
import com.ibm.bluemix.services.watson.alchemylanguage.model.AlchemyLanguageExtractTypes;

public class AlchemyLanguageService extends CloudService implements IAlchemyLanguageService {
    
    private static final String SERVICE_NAME = "AlchemyAPI-y2";
    private static final String API_URL = "http://gateway-a.watsonplatform.net/calls/text/TextGetCombinedData";
    //private static final String API_KEY = "8d206d1c3b19f8b729ed3f28f518804ea6353bf5";
    private static final String BEAN_NAME = "alchemyLanguageService";
    
    private final ObjectMapper mapper = new ObjectMapper();
    
    public AlchemyLanguageService(){
        
    }
    // access to the bean
    public static AlchemyLanguageService get() {
        return (AlchemyLanguageService) XPagesUtil.resolveVariable(BEAN_NAME);
    }
    
    public AlchemyLanguageResult analyzeText(AlchemyLanguageRequest request) {
        AlchemyLanguageResult result = null;
        try {
            super.connect();
            request.setApikey(getApiKey());
            //request.setExtract(StringUtils.join(ExtractTypes.extractTypeSelection(),","));
            request.setExtract(AlchemyLanguageExtractTypes.KEYWORDS.toString()+","+AlchemyLanguageExtractTypes.TAXONOMY.toString());
            String url = API_URL + "?" + request.toQueryString();
            
            String postDataString = mapper.writeValueAsString(request);
            String response = RestUtil.post(url, postDataString);
            
            result = mapper.readValue(response, AlchemyLanguageResult.class);
            
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
