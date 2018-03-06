package util.stat;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import crawl.market.MarketKline;
import domain.MarketDomain;
import domain.MarketMainDomain;

import java.util.List;

/**
 * Created by hzyuyongmao on 2018/3/6.
 *
 * @Description 实时
 */
public class TradeDepthAndHistoryRealStat {
    public static Cache<String, String> symbolsCache = CacheBuilder.newBuilder().build();
    private static Cache<String, Double> symbolsTradeCache = CacheBuilder.newBuilder().build();

    public static void main(String args[]) throws InterruptedException {
        while (true) {
            upAndDown("zilusdt","1min",100);
            Thread.sleep(30*1000);
        }
    }

    /*
           //1min kline,5min kline,15min kline
     */
    public static void upAndDown(String symbol, String period, int size) {
        MarketMainDomain market1MinDomain = MarketKline.getMarketKline(symbol, period, size);
        List<MarketDomain> market1MinData = market1MinDomain.getData();
        int dataSize = market1MinData.size();
        double rate = 0;
        for (int i = dataSize - 1; i >= 0; i--) {
            MarketDomain md = market1MinData.get(i);
//            System.out.println(md);
            if (md.getVol() == 0 || md.getAmount() == 0) {
                continue;
            }
            double mean = md.getVol() / md.getAmount();//trade mean
            if (i == dataSize - 1) {
                rate = mean;
            } else {
                double prof = rate / mean - 1;
                rate += prof;
            }
        }
        System.out.println("rate:" + rate);
    }


}
