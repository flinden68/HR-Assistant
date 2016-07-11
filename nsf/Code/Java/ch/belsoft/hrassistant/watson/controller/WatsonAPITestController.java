package ch.belsoft.hrassistant.watson.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ibm.bluemix.services.watson.toneanalyzer.ToneAnalyzerRequest;

import ch.belsoft.hrassistant.config.dao.ConfigurationDAO;
import ch.belsoft.hrassistant.config.model.ConfigDefault;
import ch.belsoft.hrassistant.config.model.ConfigType;
import ch.belsoft.hrassistant.config.model.IConfiguration;
import ch.belsoft.hrassistant.controller.ControllerBase;
import ch.belsoft.hrassistant.controller.IGuiController;
import ch.belsoft.tools.Logging;
import ch.belsoft.tools.XPagesUtil;
import ch.belsoft.tools.fa.FontAwesomeIcons;

public class WatsonAPITestController extends ControllerBase implements
		IGuiController<ToneAnalyzerRequest>, Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private static final String PAGETITLE = "Watson API Test";

	private ToneAnalyzerRequest toneAnalyzerRequest = new ToneAnalyzerRequest();

	public WatsonAPITestController() {

	}

	public ToneAnalyzerRequest getDataContext() {
		return toneAnalyzerRequest;
	}

	public String getPageTitle() {
		return PAGETITLE;
	}

	public void analizeText(){
		
	}
	
	public ToneAnalyzerRequest getToneAnalyzerRequest() {
		return toneAnalyzerRequest;
	}

	public void setToneAnalyzerRequest(ToneAnalyzerRequest toneAnalyzerRequest) {
		this.toneAnalyzerRequest = toneAnalyzerRequest;
	}

}
