package quant.tendencyStat.otherStat;

import domain.MarketDomain;
import domain.stat.otherStat.OtherStatDomain;
import jdbc.impl.MarketDaoImpl;
import util.CacheUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzyuyongmao on 2018/6/20.
 *
 * @Description 振幅
 */
public class Amplitude {
    public static void main(String args[]) {
        MarketDaoImpl marketDao = new MarketDaoImpl();
        List<String> symobls = marketDao.getSymbols();
        List<Double> ampList = new ArrayList<Double>();
        for (String symbol : symobls) {
            List<MarketDomain> marketDomains = marketDao.getMultiPeriodKlineData(symbol, "getKline1MinData");
            if (!symbol.equals("zilusdt")) {
                continue;
            }
            double lastClose = 0;
            for (MarketDomain marketDomain : marketDomains) {
                if (lastClose == 0) {
                    ampList.add(0D);
                    lastClose = marketDomain.getClose();
                    continue;
                }
                ampList.add((marketDomain.getHigh() - marketDomain.getLow()) / lastClose);
                lastClose = marketDomain.getClose();
            }

        }
        System.out.println(ampList);

    }

    public void getAmplitudeStat(List<MarketDomain> marketDomains, String symbol) {
        OtherStatDomain otherStatDomain = CacheUtil.otherStateCache.getIfPresent(symbol);
        if (otherStatDomain == null) {
            otherStatDomain = new OtherStatDomain();
            otherStatDomain.setSymbol(symbol);
            CacheUtil.otherStateCache.put(symbol, otherStatDomain);
        }
        double lastClose = 0;
        List<Double> ampList = new ArrayList<Double>();
        for (MarketDomain marketDomain : marketDomains) {
            if (lastClose == 0) {
                ampList.add(0D);
                lastClose = marketDomain.getClose();
                continue;
            }
            ampList.add((marketDomain.getHigh() - marketDomain.getLow()) / lastClose);
            lastClose = marketDomain.getClose();
        }
        otherStatDomain.setAmplitudeList(ampList);
    }

}
