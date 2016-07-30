package ch.belsoft.tools.fa;

import java.util.Arrays;

public enum FontAwesomeIcons {
	COGS("cogs"), BUILDING("building"), PAPER_PLANE("paper-plane"), LINK("link"), DATABASE(
			"database"), GRADUATION_CAP("graduation-cap"), DASHBOARD("dashboard");

	private final String icon;

	/**
	 * @param text
	 */
	private FontAwesomeIcons(final String icon) {
		this.icon = icon;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return icon;
	}

	/*
	 * public static FontAwesomeIcons fromString(String text) { if (text !=
	 * null) { for (FontAwesomeIcon b : FontAwesomeIcon.values()) { if
	 * (text.equalsIgnoreCase(b.icon)) { return b; } } } return null; }
	 */
	public static String[] iconSelection() {
		FontAwesomeIcons[] icons = values();
		String[] selectionItem = new String[icons.length];

		for (int i = 0; i < icons.length; i++) {
			selectionItem[i] = icons[i].toString();
		}

		Arrays.sort(selectionItem);
		return selectionItem;
	}
}
