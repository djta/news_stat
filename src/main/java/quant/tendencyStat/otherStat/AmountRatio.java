package quant.tendencyStat.otherStat;

import domain.MarketDomain;
import domain.stat.otherStat.OtherStatDomain;
import jdbc.impl.MarketDaoImpl;
import util.CacheUtil;

import java.util.List;

/**
 * Created by hzyuyongmao on 2018/6/20.
 *
 * @Description 量比
 */
public class AmountRatio {

    public static void main(String args[]) {
        MarketDaoImpl marketDao = new MarketDaoImpl();
        List<String> symobls = marketDao.getSymbols();
        for (String symbol : symobls) {
            List<MarketDomain> marketDomains = marketDao.getMultiPeriodKlineData(symbol, "getKline1dayData");
            if (!symbol.equals("zilusdt")) {
                continue;
            }
//            if (marketDomains.size() == 0) {
//                continue;
//            }
            double sumAmount = 0;
            for (MarketDomain marketDomain : marketDomains) {
                sumAmount += marketDomain.getAmount();

            }
            System.out.println(sumAmount / 7200);
        }

    }

    public static void setAmount5DayAvg(List<MarketDomain> marketDomains, String symbol) {
        OtherStatDomain otherStatDomain = CacheUtil.otherStateCache.getIfPresent(symbol);
        if (otherStatDomain == null) {
            otherStatDomain = new OtherStatDomain();
            otherStatDomain.setSymbol(symbol);
            CacheUtil.otherStateCache.put(symbol, otherStatDomain);
        }
        double sumAmount = 0;
        for (MarketDomain marketDomain : marketDomains) {
            sumAmount += marketDomain.getAmount();
        }
        otherStatDomain.setAmountPer1Min(sumAmount / 7200);
    }

}
