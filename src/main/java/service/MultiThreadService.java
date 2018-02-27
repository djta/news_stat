package service;

import domain.SymbolsDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by hzyuyongmao on 2018/2/27.
 *
 * @Description
 */
public class MultiThreadService {
    private static final Logger logger = LoggerFactory.getLogger(MultiThreadService.class);

    private static ThreadPoolExecutor executorService = new ThreadPoolExecutor(10, 10, 60, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(30));

//    public List<SymbolsDomain> runCrawlTask() {
//        int queue = executorService.getQueue().size();
//        if (queue > 5) {
//            logger.info("queue size :" + queue);
//        }
//        Future<List<SymbolsDomain>> future = executorService.submit(new CrawlMarketTask());
//        List<SymbolsDomain> resultList = null;
//        try {
//            resultList = future.get(5, TimeUnit.SECONDS);
//        } catch (Exception e) {
//            resultList = new ArrayList<SymbolsDomain>();
//            e.printStackTrace();
//        } finally {
//            future.cancel(true);
//        }
//        return resultList;
//    }
}
