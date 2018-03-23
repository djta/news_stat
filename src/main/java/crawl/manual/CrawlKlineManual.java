package crawl.manual;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import crawl.market.MarketKline;
import domain.*;
import jdbc.impl.CommonDaoImpl;
import jdbc.impl.MarketDaoImpl;
import util.Constants;
import util.HttpUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by hzyuyongmao on 2018/3/9.
 *
 * @Description
 */
public class CrawlKlineManual {
    private static Set<String> symbols = new HashSet<String>();

    public static void main(String args[]) {
        //update symbols
        String result = HttpUtil.doGetData(Constants.URL_SYMBOLS);
        System.out.println(result);
        SymbolsMainDomain smd = JSON.parseObject(result, SymbolsMainDomain.class);
        CommonDaoImpl cdi = new CommonDaoImpl();
        for (SymbolsDomain sd : smd.getData()) {
            //update mysql
            cdi.insertSymbols(sd);
//            System.out.println(sd.getBase_currency() + sd.getQuote_currency());
            symbols.add(sd.getBase_currency() + sd.getQuote_currency());
        }

        //kline to mysql
        for (String symbol : symbols) {
            MarketMainDomain mtd = MarketKline.getMarketKline(symbol, "1min", 2000);
            MarketDaoImpl marketDao = new MarketDaoImpl();
            if (mtd.getStatus().equals("ok")) {
                List<MarketDomain> list = mtd.getData();
                for (MarketDomain md : list) {
                    md.setSymbol(symbol);
                    marketDao.insertMarket(md, "kline1min");
                }
            }
            System.out.println("finish:" + symbol);
        }
    }
}
