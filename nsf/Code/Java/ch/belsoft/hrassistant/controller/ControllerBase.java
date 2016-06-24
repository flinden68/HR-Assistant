package ch.belsoft.hrassistant.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Vector;

import ch.belsoft.hrassistant.config.model.ConfigType;
import ch.belsoft.hrassistant.config.model.ConfigDefault;
import ch.belsoft.hrassistant.config.model.IConfiguration;
import ch.belsoft.hrassistant.controller.ApplicationController;
import ch.belsoft.hrassistant.controller.IGuiController;
import ch.belsoft.tools.Util;
import ch.belsoft.tools.XPagesUtil;

public abstract class ControllerBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String PARAM_ID = "id";
	protected ApplicationController applicationController = null;
	protected boolean newDataItem = false;

	public ApplicationController getApplicationController() {
		return applicationController;
	}

	public void setApplicationController(
			ApplicationController applicationController) {
		this.applicationController = applicationController;
	}

	protected String getId() {
		return XPagesUtil.getQueryString(PARAM_ID);
	}

}
