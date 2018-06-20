package quant.tendencyStat.otherStat;

import crawl.market.MarketDepth;
import domain.MarketDepthDomain;
import domain.MarketDepthMainDomain;
import domain.stat.TradeDepthStatDomain;
import domain.stat.otherStat.OtherStatDomain;
import util.CacheUtil;
import util.DateUtil;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by hzyuyongmao on 2018/6/20.
 * https://baike.baidu.com/item/%E5%A7%94%E6%AF%94%E5%A7%94%E5%B7%AE/4968271?fr=aladdin
 *
 * @Description 委比，委差
 */
public class MarketDepthStat {
    public static void main(String args[]) {
        MarketDepthMainDomain marketDepthMainDomain = MarketDepth.getDepthData("zilusdt", "step0");
        if (marketDepthMainDomain.getStatus().equals("ok")) {
            System.out.println("系统时间：" + DateUtil.ts2DateStr(String.valueOf(System.currentTimeMillis())));
            System.out.println("响应时间：" + DateUtil.ts2DateStr(String.valueOf(marketDepthMainDomain.getTs())));
            System.out.println("数据生成时间：" + DateUtil.ts2DateStr(String.valueOf(marketDepthMainDomain.getTick().getTs())));
            System.out.println(marketDepthMainDomain);
            String symbol = marketDepthMainDomain.getSymbol();
            getMarketDepthRatio(marketDepthMainDomain, symbol);
        }
        OtherStatDomain otherStatDomain = CacheUtil.otherStateCache.getIfPresent("zilusdt");
        System.out.println(otherStatDomain);
    }

    public static void getMarketDepthRatio(MarketDepthMainDomain marketDepthMainDomain, String symbol) {
        OtherStatDomain otherStatDomain = CacheUtil.otherStateCache.getIfPresent(symbol);
        if (otherStatDomain == null) {
            otherStatDomain = new OtherStatDomain();
            otherStatDomain.setSymbol(symbol);
            CacheUtil.otherStateCache.put(symbol, otherStatDomain);
        }

        MarketDepthDomain marketDepthDomain = marketDepthMainDomain.getTick();
        List<List> asks = marketDepthDomain.getAsks();
        List<List> bids = marketDepthDomain.getBids();
        double asksAmount = 0;//卖盘量
        for (List ask : asks) {

            BigDecimal amount = new BigDecimal(ask.get(1).toString());
            asksAmount += amount.doubleValue();
        }
//        System.out.println(asksAmount);
        double bidAmount = 0;//买盘量
        for (List bid : bids) {
            BigDecimal amount = new BigDecimal(bid.get(1).toString());
            bidAmount += amount.doubleValue();
        }
        //委比
        otherStatDomain.setEntrustRatio((bidAmount - asksAmount) / (bidAmount + asksAmount));
        //委差
        otherStatDomain.setEntrustSubtract(bidAmount - asksAmount);

    }
}
