package ch.belsoft.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class DateUtils {
    private static final Map<String, String> DATE_FORMAT_REGEXPS = new HashMap<String, String>() {{
        put("^\\d{8}$", "yyyyMMdd");
        put("^\\d{1,2}-\\d{1,2}-\\d{4}$", "dd-MM-yyyy");
        put("^\\d{4}-\\d{1,2}-\\d{1,2}$", "yyyy-MM-dd");
        put("^\\d{1,2}/\\d{1,2}/\\d{4}$", "MM/dd/yyyy");
        put("^\\d{4}/\\d{1,2}/\\d{1,2}$", "yyyy/MM/dd");
        put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}$", "dd MMM yyyy");
        put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}$", "dd MMMM yyyy");
        put("^\\d{12}$", "yyyyMMddHHmm");
        put("^\\d{8}\\s\\d{4}$", "yyyyMMdd HHmm");
        put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}$", "dd-MM-yyyy HH:mm");
        put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy-MM-dd HH:mm");
        put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}$", "MM/dd/yyyy HH:mm");
        put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy/MM/dd HH:mm");
        put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMM yyyy HH:mm");
        put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMMM yyyy HH:mm");
        put("^\\d{14}$", "yyyyMMddHHmmss");
        put("^\\d{8}\\s\\d{6}$", "yyyyMMdd HHmmss");
        put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd-MM-yyyy HH:mm:ss");
        put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy-MM-dd HH:mm:ss");
        put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "MM/dd/yyyy HH:mm:ss");
        put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy/MM/dd HH:mm:ss");
        put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMM yyyy HH:mm:ss");
        put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMMM yyyy HH:mm:ss");
    }};
    
    public DateUtils() {

    }

    /***************************
     * Generic Date Methods
     ***************************/
    public static String determineDateFormat(String dateString) {
        for (String regexp : DATE_FORMAT_REGEXPS.keySet()) {
            if (dateString.toLowerCase().matches(regexp)) {
                return DATE_FORMAT_REGEXPS.get(regexp);
            }
        }
        return null; // Unknown format.
    }
    
    public static long getDateInMilliseconds(Date date){
        return date.getTime();
    }
    
    public static Date getDateFromMilliseconds(long timeStamp){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        return calendar.getTime();
    }
    
    public static String getDateToString(Date date, String dateFormat){
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(date);
    }
    
    public static Calendar convertStringToCalendar(String calendarString, String calendarFormat) throws ParseException{
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(calendarFormat);
        cal.setTime(sdf.parse(calendarString));
        return cal;
    }
    
    public static String convertCalendarToString(Calendar calendar, String calendarFormat) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat(calendarFormat);
        return sdf.format(calendar.getTime());
    }
    
    public static String convertDateToString(Date date, String calendarFormat) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat(calendarFormat);
        return sdf.format(date);
    }
    
    public static Date convertStringToDate(String dateString, String dateFormat) throws ParseException{ 
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.parse(dateString);
    }
    
    public static Calendar convertDateToCalendar(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
    
    public static Date addMonthsToDate(Date date, int month){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }
    
    public static Date addDaysToDate(Date date, int days){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }
    
    public static Date convertsDateToGMTTimezone(Date date){
        Calendar calGMT = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calGMT.setTime(date);         
        return calGMT.getTime();
    }
    
    public static boolean isTodayInBetween(Date dateStart, Date dateEnd) {
            try {
                    Date dateCheck = new Date();
                    return isDateInBetween(dateCheck, dateStart, dateEnd);
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {

            }
            return false;
    }

    public static boolean isDateInBetween(Date dateCheck, Date dateStart,
                    Date dateEnd) {
            try {
                    return dateCheck.after(dateStart) && dateCheck.before(dateEnd);
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {

            }
            return false;
    }

    public static boolean isDateBeforeToday(Date dateCheck) {
            try {
                    Date dateCompare = new Date();
                    return dateCompare.before(dateCheck);
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {

            }
            return false;
    }

    public static boolean isDateBefore(Date dateCheck, Date dateCompare) {
            try {
                    return dateCompare.before(dateCheck);
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {

            }
            return false;
    }

    public static boolean isDateAfterToday(Date dateCheck) {
            try {
                    Date dateCompare = new Date();
                    return dateCompare.after(dateCheck);
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {

            }
            return false;
    }

    public static boolean isDateAfter(Date dateCheck, Date dateCompare) {
            try {
                    return dateCompare.after(dateCheck);
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {

            }
            return false;
    }
    
    /*
     * the firstWeekendDay should be Calendar format, like Calendar.SATURDAY
     * the secondWeekendDay should be Calendar format, like Calendar.SUNDAY
     */
    public static int calculateBusinessDays(Date startDate, Date endDate, int firstWeekendDay, int secondWeekendDay) {
            Calendar startCal = Calendar.getInstance();
            startCal.setTime(startDate);

            Calendar endCal = Calendar.getInstance();
            endCal.setTime(endDate);

            int workDays = 0;
            
            //Return 0 if start and end are the same
        if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
            return 0;
        }

            if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
                    startCal.setTime(endDate);
                    endCal.setTime(startDate);
            }

            do {
                    startCal.add(Calendar.DAY_OF_MONTH, 1);
                    if (startCal.get(Calendar.DAY_OF_WEEK) != firstWeekendDay && startCal.get(Calendar.DAY_OF_WEEK) != secondWeekendDay) {
                            workDays++;
                    }
            } while (startCal.getTimeInMillis() <= endCal.getTimeInMillis());

            return workDays;
    }
    
    public static int calculateDays(Date startDate, Date endDate) {
        int presumedDays = 0;
        
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);
        
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);
        
        if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
            return 0;
        }
        
        while (startCal.before(endDate)) {  
            startCal.add(Calendar.DAY_OF_MONTH, 1);  
            presumedDays++;  
          } 
        return presumedDays;
        
    }
    
    public static int daysBetween(Date d1, Date d2)
    {
       return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    public static Date calculateEndDate(Date startDate, int duration) {
            Calendar startCal = Calendar.getInstance();

            startCal.setTime(startDate);
            for (int i = 1; i < duration; i++) {
                    startCal.add(Calendar.DAY_OF_MONTH, 1);
                    while (startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                                    || startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                            startCal.add(Calendar.DAY_OF_MONTH, 1);
                    }
            }

            return startCal.getTime();
    }
    
    public static boolean isTodayInBetweenOrEqual(Date dateStart, Date dateEnd){
            Date dateCheck = new Date();
    return isDateInBetweenOrEqual(dateCheck, dateStart, dateEnd);
    }
    
    public static boolean isDateInBetweenOrEqual(Date dateCheck, Date dateStart, Date dateEnd){
            return (dateCheck.after(dateStart) || dateCheck.equals(dateStart)) && (dateCheck.before(dateEnd) || dateCheck.equals(dateEnd));
    }
}
