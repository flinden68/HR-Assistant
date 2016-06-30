package ch.belsoft.tools;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Logging {

	private final static Logger LOGGER = Logger.getLogger("HR");

	public static void logEvent(String sEvent) {
		System.out.println(sEvent);
		LOGGER.logp(Level.INFO, "", "", sEvent);
	}

	public static void logError(Exception e) {
		e.printStackTrace();
		LOGGER.log(Level.SEVERE, "an exception was thrown", e);
	}

}
