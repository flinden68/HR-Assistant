package ch.belsoft.hrassistant.config.model;

public enum ConfigType {
	JOB_CATEGORY, GENDER, SALUTATION;

	public static String[] types() {
		ConfigType[] states = values();
		String[] names = new String[states.length];

		for (int i = 0; i < states.length; i++) {
			names[i] = states[i].name();
		}

		return names;
	}
}
