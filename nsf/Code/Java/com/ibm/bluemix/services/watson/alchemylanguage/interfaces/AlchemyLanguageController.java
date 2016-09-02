package com.ibm.bluemix.services.watson.alchemylanguage.interfaces;

import com.ibm.bluemix.services.watson.alchemylanguage.injector.AlchemyLanguageInjector;
import com.ibm.bluemix.services.watson.interfaces.WatsonController;

public interface AlchemyLanguageController extends WatsonController {

	public void analyzeTextAlchemyLanguage();

	public AlchemyLanguageInjector getAlchemyLanguageInjector();

	public void setAlchemyLanguageInjector(
			AlchemyLanguageInjector alchemyLanguageInjector);

}
