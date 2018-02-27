package service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import crawl.market.MarketKline;
import domain.MarketDomain;
import domain.MarketMainDomain;
import domain.SymbolsDomain;
import jdbc.impl.CommonDaoImpl;
import jdbc.impl.MarketDaoImpl;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by hzyuyongmao on 2018/2/27.
 *
 * @Description
 */
public class CrawlMarketTask implements Callable<String> {
    private static Cache<String, String> symbolsCache = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.HOURS).build();

    public static LinkedBlockingQueue<String> symbolQueue = new LinkedBlockingQueue<String>();

    /*
      symbols从数据库里更新cache，并更新symbols消费队列
     */
    public static void updateSymbolsCache() {
        if (symbolsCache.size() == 0) {
            CommonDaoImpl cdi = new CommonDaoImpl();
            List<SymbolsDomain> list = cdi.selectSymbols();
            for (SymbolsDomain sd : list) {
                symbolsCache.put(sd.getBase_currency() + sd.getQuote_currency(), "");
            }
        }
        symbolQueue.clear();
        symbolQueue.addAll(symbolsCache.asMap().keySet());
    }

    public String call() throws InterruptedException {
        String symbols = symbolQueue.poll();
        if(symbols==null){
            System.out.println("queue poll error:null " );
            return "null";
        }
        System.out.println("queue poll:" + symbols);
        MarketMainDomain mtd = MarketKline.getMarketKline(symbols, "1min", 5);
        MarketDaoImpl marketDao = new MarketDaoImpl();
        if (mtd.getStatus().equals("ok")) {
            List<MarketDomain> list = mtd.getData();
            for (MarketDomain md : list) {
                md.setSymbol(symbols);
                marketDao.insertMarket(md);
            }
            return "ok";
        }
        return "error";

    }

    public static void main(String args[]) throws InterruptedException {
//        symbolsCache.put("a", "b");
//        System.out.println(symbolsCache.size());
        CrawlMarketTask ct = new CrawlMarketTask();
        ct.call();
    }
}
