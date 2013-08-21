package utils;

import java.text.DateFormat;
import java.util.Date;

public class DateUtil {

    public static String readable(Long time) {
        return DateFormat.getInstance().format(new Date(time));
    }
}
