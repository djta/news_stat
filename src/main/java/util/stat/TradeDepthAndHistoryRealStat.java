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
        double init = 0;
        int step = 0;
        while (true) {
            double result = upAndDown("bchbtc", "1min", 5);
            Thread.sleep(30 * 1000);
            if(step==0){
                init=result;
            }else{
               System.out.println( "step:"+(result-init));
            }
            step++;
        }
    }

    /*
           //1min kline,5min kline,15min kline
     */
    public static double upAndDown(String symbol, String period, int size) {
        MarketMainDomain market1MinDomain = MarketKline.getMarketKline(symbol, period, size);
        List<MarketDomain> market1MinData = market1MinDomain.getData();
        int dataSize = market1MinData.size();
        double rate = 0;
        double init = 0;//old data
        for (int i = dataSize - 1; i >= 0; i--) {
            MarketDomain md = market1MinData.get(i);
//            System.out.println(md);
            if (md.getVol() == 0 || md.getAmount() == 0) {
                continue;
            }
            double mean = md.getVol() / md.getAmount();//trade mean
//            System.out.println("mean:" + mean);
            if (i == dataSize - 1) {
                init = mean;
            } else {
                double prof = (mean - init) / init;
//                System.out.println("init:" + init);
//                System.out.println("mean:" + (mean-init));
//                System.out.println("prof:" + prof);
                rate += prof;
                init = mean;
            }
        }
        System.out.println("rate:" + rate);
        return rate;
    }


}
