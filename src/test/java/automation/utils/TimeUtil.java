package automation.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.TestException;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
    private static final Logger LOGGER = LogManager.getLogger();

    private TimeUtil() {
    }

    public static void sleep(long seconds){
        try{
        	long millis = seconds * 1000;
            Thread.sleep(millis);
        } catch (Exception ex){
            LOGGER.error("Thread sleep error.", ex);
            throw new TestException("Thread sleep error.", ex);
        }
    }

    public static void sleep(Duration duration){
        try{
            long millis = (long) (duration.getSeconds() * 1000);
            Thread.sleep(millis);
        }catch (Exception ex){
            LOGGER.error("Thread sleep error.", ex);
            throw new TestException("Thread sleep error.", ex);
        }
    }

    public static String getDateFormat(String simpleDateFormat) {
        SimpleDateFormat dFormat = new SimpleDateFormat(simpleDateFormat);
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        return dFormat.format(date);
    }

    public static String getFullDateTime() {
        return getDateFormat("yyyy-MM-dd_HH.mm.ss.SSS");
    }

    public static String getCurrentTimeWithMin() {
        return getDateFormat("MM_dd_HH_mm");
    }

    public static String getToday(){
        return getDateFormat("dd-MM-yyyy");
    }

}
