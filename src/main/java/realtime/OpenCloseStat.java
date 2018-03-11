package realtime;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import domain.MarketDomain;
import domain.MarketMainDomain;
import domain.realtime.OpenCloseStatDomain;
import jdbc.impl.RealtimeDaoImpl;
import service.MultiThreadService;
import util.DateUtil;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by 范志伟 on 2018-03-11.
 */
public class OpenCloseStat {
    private static Cache<String, OpenCloseStatDomain> symbolsCache = CacheBuilder.newBuilder()
            .expireAfterWrite(5, TimeUnit.HOURS).build();

    public static void main(String args[]) throws InterruptedException, ExecutionException, TimeoutException {
        RealtimeDaoImpl rdi = new RealtimeDaoImpl();
        List<OpenCloseStatDomain> openCloseList = rdi.selectDiffValue();
        for (OpenCloseStatDomain domain : openCloseList) {
            symbolsCache.put(domain.getSymbol(), domain);
        }
        while (true) {
            List<MarketMainDomain> resultDomain = KlineRealTimeService.getMarketKlineInfo("1min", 1);
            for (MarketMainDomain mmd : resultDomain) {
                String symbol = mmd.getSymbol();
                OpenCloseStatDomain openCloseDomain = symbolsCache.getIfPresent(symbol);
                if (openCloseDomain == null) {
                    continue;
                }
                //疑似异常值分析
                double max = openCloseDomain.getMax_index();
                double min = openCloseDomain.getMin_index();
                for (MarketDomain md : mmd.getData()) {
                    double diff = md.getClose() - md.getOpen();
                    if (diff >= max) {
                        System.out.println("up>>>>>>:" + DateUtil.ts2Date(md.getId()) + "\t" + md.getSymbol());
                    } else if (diff <= min) {
                        System.out.println("down>>>>>:" + DateUtil.ts2Date(md.getId()) + "\t" + md.getSymbol());
                    } else {
//                    System.out.println("wait:"+md);
                    }
                }
            }
            Thread.sleep(15*2000);
        }


    }

    //
    public static void outlierValueModel() {
        RealtimeDaoImpl rdi = new RealtimeDaoImpl();
        List<OpenCloseStatDomain> openCloseList = rdi.selectDiffValue();
        for (OpenCloseStatDomain domain : openCloseList) {
            symbolsCache.put(domain.getSymbol(), domain);
        }
        MultiThreadService mult = new MultiThreadService();

    }


}
