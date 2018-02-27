package service;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by hzyuyongmao on 2018/2/27.
 *
 * @Description
 */
public class test {
    private static ThreadPoolExecutor es = new ThreadPoolExecutor(10, 10, 100L, TimeUnit.SECONDS, new LinkedBlockingQueue(30), new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String args[]) {
        long t = System.currentTimeMillis();
        CrawlMarketTask.updateSymbolsCache();
        int size = CrawlMarketTask.symbolQueue.size();
        for (int i = 0; i < size; i++) {
            es.submit(new CrawlMarketTask());
        }
        es.shutdown();
        long cost = System.currentTimeMillis() - t;
        System.out.println("cost:" + cost);
    }

}
