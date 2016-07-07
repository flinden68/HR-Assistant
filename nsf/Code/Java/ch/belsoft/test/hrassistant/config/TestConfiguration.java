package ch.belsoft.test.hrassistant.config;

import java.util.Collection;
import java.util.List;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import ch.belsoft.hrassistant.config.controller.ConfigurationController;
import ch.belsoft.hrassistant.config.dao.ConfigurationDAO;
import ch.belsoft.hrassistant.config.model.ConfigDefault;
import ch.belsoft.tools.Logging;
import ch.belsoft.tools.XPagesUtil;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class TestConfiguration {

	private static final String BEAN_CONFIGURATIONCONTROLLER = "configurationController";
	private static final String ID_MESSAGEPANEL = "pnlTestResults";

	private ConfigurationController getConfigurationController() {
		ConfigurationController configurationController = (ConfigurationController) XPagesUtil
				.getBindingValue(XPagesUtil
						.getBindingValueName(BEAN_CONFIGURATIONCONTROLLER));
		return configurationController;
	}

	@Test
	public void testConfigList() {
		try {
			XPagesUtil
					.showErrorMessage("Start testConfigList", ID_MESSAGEPANEL);
			List<ConfigDefault> list = this.getConfigurationController()
					.getConfigurations();
			assertThat(list.isEmpty(), is(false));
		} catch (Exception e) {
			Logging.logError(e);
		}
	}

	public void test() {
		Result result = JUnitCore.runClasses(TestConfiguration.class);
		for (Failure failure : result.getFailures()) {
			XPagesUtil.showErrorMessage(failure.toString(), ID_MESSAGEPANEL);
			// System.out.println();
		}
		XPagesUtil.showErrorMessage("" + result.wasSuccessful(),
				ID_MESSAGEPANEL);
	}
}
