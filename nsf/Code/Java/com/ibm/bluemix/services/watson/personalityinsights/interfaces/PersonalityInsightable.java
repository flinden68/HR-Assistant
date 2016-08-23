package com.ibm.bluemix.services.watson.personalityinsights.interfaces;

import com.ibm.bluemix.services.watson.personalityinsights.model.PersonalityInsightsResult;

public interface PersonalityInsightable {
	public String getName();

	public PersonalityInsightsResult getPersonalityInsightsResult();

	public void setPersonalityInsightsResult(
			PersonalityInsightsResult personalityInsightsResult);

	public String getTextToAnalyze();

	public void setTextToAnalyze(String textToAnalyze);
}
