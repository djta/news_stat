package service;

import java.math.BigDecimal;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by hzyuyongmao on 2018/2/27.
 *
 * @Description
 */
public class test {
//    private static ThreadPoolExecutor es = new ThreadPoolExecutor(10, 10, 100L, TimeUnit.SECONDS, new LinkedBlockingQueue(30), new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String args[]) {
        MultiThreadService mt = new MultiThreadService();
        mt.getMarketKlineInfo("5min", 1000, "kline5min");
    }

}
