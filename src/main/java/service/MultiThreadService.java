package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Created by hzyuyongmao on 2018/2/27.
 *
 * @Description
 */
public class MultiThreadService {
    private static final Logger logger = LoggerFactory.getLogger(MultiThreadService.class);

    public void getMarketKlineInfo(String period, int returnSize, String queryid) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 100L, TimeUnit.SECONDS, new LinkedBlockingQueue(30), new ThreadPoolExecutor.CallerRunsPolicy());
        CrawlMarketKlineTask.updateSymbolsCache();
        int size = CrawlMarketKlineTask.symbolQueue.size();
        for (int i = 0; i < size; i++) {
            threadPoolExecutor.submit(new CrawlMarketKlineTask(period, returnSize, queryid));
        }
        threadPoolExecutor.shutdown();
    }
}
