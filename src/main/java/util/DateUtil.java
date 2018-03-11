package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 范志伟 on 2018-03-11.
 */
public class DateUtil {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String args[]) throws ParseException {
        String str = "2018-02-28 21:31:00";
        Date date = simpleDateFormat.parse(str);
        long ts = date.getTime();
        String res = String.valueOf(ts);
       System.out.println(res);

    }
    public static String ts2Date(long ts) {
        Date date = new Date(ts * 1000);
        return simpleDateFormat.format(date);
    }

    public static long date2ts(String dateStr) throws ParseException {
        Date date = simpleDateFormat.parse(dateStr);
        long ts = date.getTime();
        return ts;
    }

}
