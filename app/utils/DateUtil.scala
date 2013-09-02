package utils;

import java.text.DateFormat;
import java.util.Date;

object DateUtil {

    def readable(time: Long):String = {
        DateFormat.getInstance().format(new Date(time))
    }
}
