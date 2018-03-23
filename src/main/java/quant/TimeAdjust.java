package quant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hzyuyongmao on 2018/3/23.
 *
 * @Description
 */
public class TimeAdjust {
    private long ts0;//当前时间(分钟级别)
    private long ts1;//当前时间-1min
    private long ts2;//当前时间-2min

    public long getTs0() {
        return ts0;
    }

    public long getTs1() {
        return ts1;
    }

    public long getTs2() {
        return ts2;
    }

    @Override
    public String toString() {
        return "TimeAdjust{" +
                "ts0=" + ts0 +
                ", ts1=" + ts1 +
                ", ts2=" + ts2 +
                '}';
    }

    public static void main(String args[]) throws ParseException {
        long currentTs = System.currentTimeMillis();
        System.out.println(currentTs);
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.out.println(sdf.format(d));
        String tes = sdf.format(d);
        System.out.println(sdf.parse(tes).getTime() / 1000);
        System.out.println(getMinDiff(1521797760, 3));
    }

    /*
      1分钟级别
     */
    public static boolean getMinDiff(long ts, int minType) {
        long currentTs = System.currentTimeMillis() / 1000;
        if (currentTs - ts < 60 * minType) {
            return true;
        } else {
            return false;
        }
    }

    /*
       十秒级别
     */
    public static boolean getSecondDiff(long ts, int tenSecond) {
        long currentTs = System.currentTimeMillis() / 1000;
        if (currentTs - ts < 10 * tenSecond) {
            return true;
        } else {
            return false;
        }
    }

}
