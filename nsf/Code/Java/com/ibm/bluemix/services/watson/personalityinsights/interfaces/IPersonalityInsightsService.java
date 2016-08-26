package com.ibm.bluemix.services.watson.personalityinsights.interfaces;

import com.ibm.bluemix.services.watson.personalityinsights.model.PersonalityInsightsRequest;
import com.ibm.bluemix.services.watson.personalityinsights.model.PersonalityInsightsResult;

public interface IPersonalityInsightsService {
	/**
	 * @param req
	 *            Request object filled with ContentItems
	 * @return Result object
	 */
	public PersonalityInsightsResult analyzePersonalityInsights(
			PersonalityInsightsRequest req);
}
