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
        BigDecimal bd3 = new BigDecimal("0");
        BigDecimal bd1 = new BigDecimal("1");
        BigDecimal bd2 = new BigDecimal("3");
        bd2 = bd2.add(bd1);
        bd3.add(bd2);
        System.out.println(bd2);
        System.out.println(bd3);
    }

}
