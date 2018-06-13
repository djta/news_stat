package quant.multiPeriod;

import domain.MarketDomain;
import jdbc.impl.MarketDaoImpl;
import quant.onlinebacktest.TradeContextOnline;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzyuyongmao on 2018/6/13.
 *
 * @Description
 */
public class MultiPeriodMain {
    public static void main(String args[]) {
        MarketDaoImpl marketDao = new MarketDaoImpl();
        List<String> symobls = marketDao.getSymbols();

        for (String symbol : symobls) {
            if (!symbol.equals("btmusdt")) {
                continue;
            }
            List<MarketDomain> biggerMarketDomains = marketDao.getMultiPeriodKlineData(symbol, "getKline5MinData");
            List<MarketDomain> smallerMarketDomains = marketDao.getMultiPeriodKlineData(symbol, "getKline1MinData");
            System.out.println("bigger size:" + biggerMarketDomains.size() + "\tsmall size:" + smallerMarketDomains.size());

            for (int i = biggerMarketDomains.size() - 1; i > 250; i--) {
                long biggerTs = biggerMarketDomains.get(i).getId();
//                System.out.println("biggerTs:" + biggerTs);
//                for (MarketDomain smallerMarketDomain : smallerMarketDomains) {
                List<MarketDomain> biggerList = biggerMarketDomains.subList(i - 250, i);
                System.out.println("smallerTs:" + biggerList);
                break;
//                for (int j = smallerMarketDomains.size() - 1; j > 250; j--) {
//                    long smallerTs = smallerMarketDomains.get(j).getId();
//                    System.out.println("biggerTs:" + biggerTs);
//                    System.out.println("smallerTs:" + smallerTs);
//                    if (biggerTs < (smallerTs - 300) || biggerTs > (smallerTs + 300)) {
//                        continue;
//                    }
////                    System.out.println("biggerTs:" + biggerTs);
////                    System.out.println("smallerTs:" + smallerTs);
//                    List<MarketDomain> smallerList = smallerMarketDomains.subList(j - 250, j);
//                }

            }


        }
    }
}
