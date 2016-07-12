package ch.belsoft.charts.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ibm.bluemix.services.watson.toneanalyzer.ToneAnalyzerRequest;
import com.ibm.bluemix.services.watson.toneanalyzer.ToneAnalyzerResult;
import com.ibm.bluemix.services.watson.toneanalyzer.ToneAnalyzerService;

import ch.belsoft.hrassistant.config.dao.ConfigurationDAO;
import ch.belsoft.hrassistant.config.model.ConfigDefault;
import ch.belsoft.hrassistant.config.model.ConfigType;
import ch.belsoft.hrassistant.config.model.IConfiguration;
import ch.belsoft.hrassistant.controller.ControllerBase;
import ch.belsoft.hrassistant.controller.IGuiController;
import ch.belsoft.tools.Logging;
import ch.belsoft.tools.XPagesUtil;
import ch.belsoft.tools.fa.FontAwesomeIcons;

public class Chart implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private List<String> labels = new ArrayList<String>();
	@JsonProperty("datasets")
	private List<DataSet> dataSets = new ArrayList<DataSet>();

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public void addLabel(String label) {
		this.labels.add(label);
	}

	public List<DataSet> getDataSets() {
		return dataSets;
	}

	public void setDataSets(List<DataSet> dataSets) {
		this.dataSets = dataSets;
	}

	public void addDataSet(DataSet dataSet) {
		this.dataSets.add(dataSet);
	}

}
