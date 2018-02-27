package quartzJob;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import service.CrawlMarketTask;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by 范志伟 on 2018-02-27.
 */
public class Market1MinJob implements Job {
    private static ThreadPoolExecutor es = new ThreadPoolExecutor(10, 10, 100L, TimeUnit.SECONDS, new LinkedBlockingQueue(30), new ThreadPoolExecutor.CallerRunsPolicy());

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 100L, TimeUnit.SECONDS, new LinkedBlockingQueue(30), new ThreadPoolExecutor.CallerRunsPolicy());
        long t = System.currentTimeMillis();
        CrawlMarketTask.updateSymbolsCache();
        int size = CrawlMarketTask.symbolQueue.size();
        for (int i = 0; i < size; i++) {
            threadPoolExecutor.submit(new CrawlMarketTask());
        }
        threadPoolExecutor.shutdown();
        long cost = System.currentTimeMillis() - t;
        System.out.println("cost:" + cost);
    }

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
