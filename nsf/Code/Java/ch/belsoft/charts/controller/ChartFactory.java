package ch.belsoft.charts.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ibm.bluemix.services.watson.toneanalyzer.ToneAnalyzerRequest;
import com.ibm.bluemix.services.watson.toneanalyzer.ToneAnalyzerResult;
import com.ibm.bluemix.services.watson.toneanalyzer.ToneAnalyzerService;
import com.ibm.bluemix.services.watson.toneanalyzer.ToneCategory;

import ch.belsoft.charts.model.Chart;
import ch.belsoft.hrassistant.config.dao.ConfigurationDAO;
import ch.belsoft.hrassistant.config.model.ConfigDefault;
import ch.belsoft.hrassistant.config.model.ConfigType;
import ch.belsoft.hrassistant.config.model.IConfiguration;
import ch.belsoft.hrassistant.controller.ControllerBase;
import ch.belsoft.hrassistant.controller.IGuiController;
import ch.belsoft.tools.Logging;
import ch.belsoft.tools.XPagesUtil;
import ch.belsoft.tools.fa.FontAwesomeIcons;
 
public class ChartFactory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Chart createToneAnalyzerChartTone(
			ToneAnalyzerResult toneAnalyzerResult) {

		Chart result = new Chart();

		try {
			for (ToneCategory toneCategory : toneAnalyzerResult
					.getToneCategories()) {
				result.addLabel(toneCategory.getCategory_name());
			}
		} catch (Exception e) {
			Logging.logError(e);
		}

		return new Chart();
	}
}
