package service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import domain.SymbolsDomain;
import jdbc.impl.CommonDaoImpl;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by hzyuyongmao on 2018/2/27.
 *
 * @Description
 */
public class CrawlTask implements Callable<List<SymbolsDomain>> {
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
        symbolQueue.addAll(symbolsCache.asMap().keySet());
    }

    public List<SymbolsDomain> call() throws InterruptedException {
        String symbols = symbolQueue.poll();
        System.out.println("queue poll:" + symbols);
        List<SymbolsDomain> list = new ArrayList<SymbolsDomain>();
        Thread.sleep(5000);
        return list;
    }

    public static void main(String args[]) throws InterruptedException {
//        symbolsCache.put("a", "b");
//        System.out.println(symbolsCache.size());
        CrawlTask ct = new CrawlTask();
        ct.call();
    }
}
