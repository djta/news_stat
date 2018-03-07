import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import crawl.market.MarketDepth;

import java.util.concurrent.TimeUnit;

/**
 * Created by 范志伟 on 2018-03-05.
 */
public class EarnMoneyTest {
    private static Cache<String, String> symbolsCache = CacheBuilder.newBuilder().build();

    public static void main(String args[]) {
         symbolsCache.put("htusdt","up");
         for(String symbol:symbolsCache.asMap().keySet()){
             MarketDepth.getDepthData(symbol,"step1");

         }

    }
}
