package ch.belsoft.charts.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataSet implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private String label = "";
	private List<Float> data = new ArrayList<Float>();

	private List<String> backgroundColor = new ArrayList<String>();;
	private String borderColor = "rgba(179,181,198,1)";
	private String pointBackgroundColor = "rgba(179,181,198,1)";
	private String pointBorderColor = "#fff";
	private String pointHoverBackgroundColor = "#fff";
	private String pointHoverBorderColor = "rgba(179,181,198,1)";

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<Float> getData() {
		return data;
	}

	public void setData(List<Float> data) {
		this.data = data;
	}

	public void addData(float data) {
		this.data.add(data);
	}

	/*
	 * public String getBackgroundColor() { return backgroundColor; }
	 * 
	 * public void setBackgroundColor(String backgroundColor) {
	 * this.backgroundColor = backgroundColor; }
	 */

	public void addBackgroundColor(String backgroundColor) {
		this.backgroundColor.add(backgroundColor);
	}

	public String getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	public String getPointBackgroundColor() {
		return pointBackgroundColor;
	}

	public void setPointBackgroundColor(String pointBackgroundColor) {
		this.pointBackgroundColor = pointBackgroundColor;
	}

	public String getPointBorderColor() {
		return pointBorderColor;
	}

	public void setPointBorderColor(String pointBorderColor) {
		this.pointBorderColor = pointBorderColor;
	}

	public String getPointHoverBackgroundColor() {
		return pointHoverBackgroundColor;
	}

	public void setPointHoverBackgroundColor(String pointHoverBackgroundColor) {
		this.pointHoverBackgroundColor = pointHoverBackgroundColor;
	}

	public String getPointHoverBorderColor() {
		return pointHoverBorderColor;
	}

	public void setPointHoverBorderColor(String pointHoverBorderColor) {
		this.pointHoverBorderColor = pointHoverBorderColor;
	}

	public List<String> getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(List<String> backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

}
