package manul;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import crawl.market.MarketKline;
import domain.MarketDomain;
import domain.MarketMainDomain;
import domain.SymbolsDomain;
import domain.SymbolsMainDomain;
import jdbc.impl.CommonDaoImpl;
import jdbc.impl.MarketDaoImpl;
import util.Constants;
import util.HttpUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by 范志伟 on 2018-03-09.
 */
public class CrawlKLine {
    private static Set<String> symbolSet = new HashSet<String>();

    public static void main(String args[]) {
        String result = HttpUtil.doGetData(Constants.URL_SYMBOLS);
        SymbolsMainDomain smd = JSON.parseObject(result, SymbolsMainDomain.class);
        CommonDaoImpl cdi = new CommonDaoImpl();
        for (SymbolsDomain sd : smd.getData()) {
            cdi.insertSymbols(sd);
            symbolSet.add(sd.getBase_currency() + sd.getQuote_currency());
        }

        for (String symbol : symbolSet) {
            MarketMainDomain mtd = MarketKline.getMarketKline(symbol, "1min", 2000);
            MarketDaoImpl marketDao = new MarketDaoImpl();
            if (mtd.getStatus().equals("ok")) {
                List<MarketDomain> list = mtd.getData();
                for (MarketDomain md : list) {
                    md.setSymbol(symbol);
                    marketDao.insertMarket(md, "kline1min");
                }

            }
            System.out.println("finish:"+symbol);
        }

    }

}
