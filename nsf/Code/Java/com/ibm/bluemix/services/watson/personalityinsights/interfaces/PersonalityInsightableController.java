package com.ibm.bluemix.services.watson.personalityinsights.interfaces;

import com.ibm.bluemix.services.watson.personalityinsights.injector.PersonalityInsightsInjector;

public interface PersonalityInsightableController {

	public PersonalityInsightsInjector getPersonalityInsightsInjector();

	public void setPersonalityInsightsInjector(
			PersonalityInsightsInjector personalityInsightsInjector);

	public void analyzeTextPersonalityInsights();

}
