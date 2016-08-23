package com.ibm.bluemix.services.watson.personalityinsights.injector;

import java.io.Serializable;

import org.apache.commons.lang3.StringEscapeUtils;

import ch.belsoft.tools.Logging;
import ch.belsoft.tools.XPagesUtil;

import com.ibm.bluemix.services.watson.personalityinsights.interfaces.PersonalityInsightable;
import com.ibm.bluemix.services.watson.personalityinsights.model.ContentItem;
import com.ibm.bluemix.services.watson.personalityinsights.model.PersonalityInsightsRequest;
import com.ibm.bluemix.services.watson.personalityinsights.service.PersonalityInsightsConsumer;
import com.ibm.bluemix.services.watson.personalityinsights.service.PersonalityInsightsService;
import com.ibm.bluemix.services.watson.toneanalyzer.service.ToneAnalyzerConsumer;

public class PersonalityInsightsInjector implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private PersonalityInsightsService personalityInsightsService = null;
	private PersonalityInsightsConsumer personalityInsightsConsumer = null;

	private static final String BEAN_NAME = "personalityInsightsInjector";

	public PersonalityInsightsInjector() {

	}

	private PersonalityInsightsConsumer getPersonalityInsightsConsumer() {
		if (personalityInsightsConsumer == null) {
			personalityInsightsConsumer = new PersonalityInsightsConsumer(
					personalityInsightsService);
		}
		return personalityInsightsConsumer;

	}

	public void setPersonalityInsightsService(
			PersonalityInsightsService personalityInsightsService) {
		this.personalityInsightsService = personalityInsightsService;
	}

	public PersonalityInsightsService getPersonalityInsightsService() {
		return personalityInsightsService;
	}

	// access to the bean
	public static PersonalityInsightsInjector get() {
		return (PersonalityInsightsInjector) XPagesUtil
				.resolveVariable(BEAN_NAME);
	}

	public void analyzePersonalityInsights(
			PersonalityInsightable personalityInsightable) {
		try {
			PersonalityInsightsRequest personalityInsightsRequest = new PersonalityInsightsRequest();
			String textToAnalyze = StringEscapeUtils
					.escapeHtml4(personalityInsightable.getTextToAnalyze());
			ContentItem contentItem = new ContentItem();
			contentItem.setContent(textToAnalyze);
			personalityInsightsRequest.addContentItem(contentItem);
			this.getPersonalityInsightsConsumer().analyzePersonalityInsights(
					personalityInsightsRequest, personalityInsightable);
		} catch (Exception e) {
			Logging.logError(e);
		}

	}

}
