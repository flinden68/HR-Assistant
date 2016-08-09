package ch.belsoft.charts.factory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import com.ibm.bluemix.services.watson.toneanalyzer.interfaces.ToneAnalyzable;
import com.ibm.bluemix.services.watson.toneanalyzer.model.Tone;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneAnalyzerResult;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneCategory;

import ch.belsoft.charts.model.Chart;
import ch.belsoft.charts.model.DataSet;

import ch.belsoft.tools.Logging;
import ch.belsoft.tools.Util;
import ch.belsoft.hrassistant.job.model.*;

public class ChartFactoryJobApplications extends ChartFactory<JobApplication>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Chart createChart(JobApplication jobApplication) {
		// makes no sense yet..
		return null;
	}

	@Override
	public Chart createChart(List<JobApplication> jobApplicationList) {
		Chart chart = new Chart();
		try {

			DataSet dataSet = new DataSet();
			dataSet.setLabel("label");

			HashMap<String, Integer> jobApplCountMap = new HashMap<String, Integer>();

			for (JobApplication jobApplication : jobApplicationList) {
				String key = jobApplication.getJob().getName();
				if (!jobApplCountMap.containsKey(key)) {
					jobApplCountMap.put(key, 1);
				} else {
					int count = jobApplCountMap.get(key) + 1;
					jobApplCountMap.put(key, count);
				}
			}

			Iterator<Map.Entry<String, Integer>> it = jobApplCountMap
					.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it
						.next();
				chart.addLabel(entry.getKey());
				dataSet.addBackgroundColor(Util.getRgbaColorOfString(entry
						.getKey(), opacity));
				dataSet.addData(entry.getValue());
			}
			/*
			 * for (JobApplication jobApplication : jobApplicationList) {
			 * System.out.println("jobApplication.getJobId():" +
			 * jobApplication.getJobId() + " bane: " +
			 * jobApplication.getJob().getName());
			 * 
			 * if (!jobApplication.getJobId().equals("")) {
			 * chart.addLabel(jobApplication.getJob().getName());
			 * dataSet.addBackgroundColor(Util.getRgbaColorOfString(
			 * jobApplication.getJob().getName(), opacity));
			 * dataSet.addData(jobApplCountMap.get(jobApplication .getJobId()));
			 * } }
			 */
			chart.addDataSet(dataSet);
		} catch (Exception e) {
			Logging.logError(e);
		}
		return chart;
	}
}
