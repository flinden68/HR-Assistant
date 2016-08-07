package ch.belsoft.charts.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ch.belsoft.charts.factory.ChartFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

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

	@Override
	public String toString() {
		return ChartFactory.getChartAsJson(this);
	}

	public void addLabel(String label) {
		if (this.labels.contains(label) == false) {
			this.labels.add(label);
		}
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
