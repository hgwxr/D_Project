package hgwxr.zs.com.d_project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;



public class TimeUtils {
    public static final SimpleDateFormat DATE_TIME_FORMAT
            = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    public static final SimpleDateFormat DATE_FORMAT
            = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public static final SimpleDateFormat TIME_FORMAT
            = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    public static final SimpleDateFormat TIME_FORMAT_NO_SECONDS
            = new SimpleDateFormat("HH:mm", Locale.getDefault());
    public static final SimpleDateFormat TIME_FORMAT_NO_ALL_SECONDS
            = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

    public static String formatDateTime(Date date) {
        return DATE_TIME_FORMAT.format(date);
    }

    public static Date parseDateTime(String time) throws ParseException {
        return DATE_TIME_FORMAT.parse(time);
    }

    public static String formatLongToString(long timeMill) {
        return formatLongToString(DATE_TIME_FORMAT,timeMill);
    }
    private static String formatLongToString(SimpleDateFormat dateFormat, long timeMill) {
        return dateFormat.format(timeMill);
    }
}
