package ch.belsoft.tools;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Vector;
import java.io.*;

import com.paulwithers.openLog.OpenLogItem;

public class Util {
	private static final String HASH = "#";

	public static void logEvent(String sError) {
		OpenLogItem
				.logEvent(null, sError, OpenLogItem.getSeverity().INFO, null);
	}

	// public static void logError(Exception e) {
	// e.printStackTrace();
	// OpenLogItem.logError(e);
	// }

	@SuppressWarnings("unchecked")
	public static ArrayList<String> convertObjectToStringList(List<Object> a) {
		return (ArrayList) a;
	}

	public static String parseBoolenToString(boolean b) {
		return b ? "1" : "0";
	}

	public static String getRgbaColorOfString(String colorfulString,
			String opacity) {
		int hash = colorfulString.hashCode();
		int r = (hash & 0xFF0000) >> 16;
		int g = (hash & 0x00FF00) >> 8;
		int b = hash & 0x0000FF;
			return String.format("rgba(%1$d,%2$d,%3$d, %4$s)", r, g, b, opacity);
	}

	/*
	 * public static String getRgbColorOfString(String colorfulString) { int
	 * hash = colorfulString.hashCode(); int r = (hash & 0xFF0000) >> 16; int g
	 * = (hash & 0x00FF00) >> 8; int b = hash & 0x0000FF; return
	 * String.format("%1$i,%2$i,%3$i", r, g, b); }
	 */

	public static String getHexColorOfString(String colorfulString) {
		Integer intColor = colorfulString.hashCode();
		String hexColor = HASH + Integer.toHexString(intColor).substring(2);
		return hexColor;
	}

	public static String parseBoolenToRealString(boolean b) {
		return b ? "true" : "false";
	}

	public static boolean toBoolean(String s) {
		try {
			return Boolean.parseBoolean(s); // Successfully converted String to
			// boolean
		} catch (Exception e) {
			return false; // There was some error, so return null.
		}
	}

	public static boolean parseBool(String s) {
		return "1".equals(s);
	}

	public static <K, V> K getFirstKey(LinkedHashMap<K, V> map) {
		Iterator<K> i = map.keySet().iterator();
		return i.hasNext() ? i.next() : null;
	}

	public static String readableFileSize(long size) {
		if (size <= 0)
			return "0";
		final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
		int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
		return new DecimalFormat("#,##0.#").format(size
				/ Math.pow(1024, digitGroups))
				+ " " + units[digitGroups];
	}

	public static String getFileExtension(String attachmentName) {
		String result = attachmentName;
		try {
			int i = attachmentName.lastIndexOf('.');
			if (i > 0) {
				result = attachmentName.substring(i + 1);
			}
		} catch (Exception e) {
			Logging.logError(e);
		}
		return result;
	}

	public static String getAttachmentNameShort(String attachmentName) {
		String result = attachmentName;

		try {
			String[] tokens = attachmentName.split("\\.(?=[^\\.]+$)");

			String base = tokens[0];

			if (tokens[0].length() > 5) {
				attachmentName = base.substring(0, 5) + "..";
				attachmentName = attachmentName + tokens[1];
			}

			result = attachmentName;
		} catch (Exception e) {
			Logging.logError(e);
		}
		return result;
	}

	public static String getUsDate(Date dt) {
		if (dt == null) {
			return "";
		} else {
			SimpleDateFormat sdfDestination = new SimpleDateFormat("MM/dd/yyyy");
			return sdfDestination.format(dt);
		}
	}

	public static String implode(List<String> lst) {
		String result = "";
		try {
			for (String s : lst) {
				if (!result.equals("")) {
					result += ", ";
				}
				result += s;
			}
		} catch (Exception e) {
			Logging.logError(e);
		}
		return result;
	}

	public static List<Double> objectToDoubleList(Object object) {
		List<Double> result = new ArrayList<Double>();
		try {
			if (!object.getClass().getName().endsWith("Vector")) {
				result.add((Double) object);
			} else {
				result = (Vector<Double>) object;
			}
		} catch (Exception e) {
			Logging.logError(e);
		}
		return result;
	}

	public static List<String> objectToStringList(Object object) {
		List<String> result = new ArrayList<String>();
		try {
			if (!object.getClass().getName().endsWith("Vector")) {
				result.add((String) object);
			} else {
				result = (Vector<String>) object;
			}
		} catch (Exception e) {
			Logging.logError(e);
		}
		return result;
	}

	public static List<String> vectorToList(Vector itemValue) {
		List<String> lst = itemValue;
		return lst;
	}

	// convert InputStream to String
	public static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}

}
