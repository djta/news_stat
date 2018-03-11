package realtime;

import domain.MarketMainDomain;
import domain.realtime.OpenCloseStatDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.CrawlMarketKlineTask;
import service.KlineTaskRealTime;
import service.MultiThreadService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by 范志伟 on 2018-03-11.
 */
public class KlineRealTimeService {
    private static final Logger logger = LoggerFactory.getLogger(MultiThreadService.class);

    public static List<MarketMainDomain> getMarketKlineInfo(String period, int returnSize) throws ExecutionException, InterruptedException, TimeoutException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(50, 50, 150L, TimeUnit.SECONDS, new LinkedBlockingQueue(30), new ThreadPoolExecutor.CallerRunsPolicy());
        KlineTaskRealTime.updateSymbolsCache();
        int size = KlineTaskRealTime.symbolQueue.size();
        List<MarketMainDomain> marketList = new ArrayList<MarketMainDomain>();
        for (int i = 0; i < size; i++) {
            Future<MarketMainDomain> future = threadPoolExecutor.submit(new KlineTaskRealTime(period, returnSize));
            marketList.add(future.get(5 * 1000, TimeUnit.MILLISECONDS));
        }
        threadPoolExecutor.shutdown();
        return marketList;
    }
}
