package ch.belsoft.hrassistant.config.model;

import java.util.Arrays;

import ch.belsoft.tools.fa.FontAwesomeIcons;

public enum ConfigParamsMenuCategory {
	HR("HR Menu"), JOB_APPLICANT("Job Applicant's Menu");

	private final String menuCategory;

	/**
	 * @param text
	 */
	private ConfigParamsMenuCategory(final String menuCategory) {
		this.menuCategory = menuCategory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.menuCategory;
	}

	public static String[] menuCategorySelection() {
		ConfigParamsMenuCategory[] menuCategories = values();
		String[] selectionItem = new String[menuCategories.length];

		for (int i = 0; i < menuCategories.length; i++) {
			selectionItem[i] = menuCategories[i].toString() + "|"
					+ menuCategories[i].name();
		}

		Arrays.sort(selectionItem);
		return selectionItem;
	}
}
