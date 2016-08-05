package ch.belsoft.hrassistant.config.controller;

import java.io.Serializable;
import java.util.*;

import ch.belsoft.hrassistant.config.model.ConfigDefault;

public class ConfigurationComparatorSort implements Comparator<ConfigDefault>,
		Serializable {

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;

	public int compare(ConfigDefault arg0, ConfigDefault arg1) {
		return arg0.getSort() < arg1.getSort() ? -1 : 1;
 
	}

	// @Override
	// public int compare(Person a, Person b) {
	// return a.name.compareToIgnoreCase(b.name);
	// }

}
