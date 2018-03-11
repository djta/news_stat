import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import crawl.market.MarketDepth;
import crawl.market.MarketHistory;
import crawl.market.MarketTradeHistory;
import domain.MarketDepthMainDomain;
import domain.MarketTradeDomain;
import domain.MarketTradeHistoryMainDomain;
import domain.MarketTradeTickDomain;
import domain.stat.TradeDepthStatDomain;
import domain.stat.TradeHistoryStatDomain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by hzyuyongmao on 2018/3/7.
 *
 * @Description
 */
public class TestStat {
    private static Cache<String, String> symbolsCache = CacheBuilder.newBuilder().build();

    public static void main(String args[]) throws InterruptedException {
        double oldbuyPrice=0;
//        while (true){
//            Thread.sleep(1000);
//            TradeHistoryStatDomain ths = getHistroyMeanStat("zilusdt", 200);
//            TradeDepthStatDomain tdsd = getDepthMean("zilusdt", "step0");
//            double buyPrice = ths.getBuyPrice();
//            double sellPrice=ths.getSellPrice();
//            double bidPrice = tdsd.getBidPrice();
//            double askPrice = tdsd.getAskPrice();
//            if(oldbuyPrice>buyPrice){
//                System.out.println("up");
//            }else if(oldbuyPrice<buyPrice){
//                System.out.println("down");
//            }else{
//                System.out.println("null");
//            }
//            oldbuyPrice=buyPrice;
//            System.out.println("buyPrice:"+buyPrice);
//            System.out.println("sellPrice:"+sellPrice);
//            System.out.println("bidPrice:"+bidPrice);
//            System.out.println("askPrice:"+askPrice);
//
//        }

        Map<String,TreeMap<String,TradeHistoryStatDomain> >map= new HashMap<String,TreeMap<String,TradeHistoryStatDomain>>();
        TreeMap<Long,String> tm=new TreeMap<Long,String>();
        tm.put(2L,"A");
        tm.put(1L,"B");
        tm.put(3L,"C");
        for(long d:tm.keySet()){
            System.out.println("key:"+d+"\tvalue:"+tm.get(d));
            tm.size();
        }

    }

    public static TradeHistoryStatDomain getHistroyMeanStat(String symbol, int size) {
        TradeHistoryStatDomain thsd = new TradeHistoryStatDomain();
        MarketTradeHistoryMainDomain tradeHistory = MarketTradeHistory.getTradeHistoryData(symbol, size);
        if (tradeHistory == null) {
            thsd.setStatus("network");
            return thsd;
        } else if (tradeHistory.getStatus().equals("error")) {
            thsd.setStatus("error");
            return thsd;
        } else {
            thsd.setStatus("ok");
        }
        thsd.setTs(tradeHistory.getTs());
        List<MarketTradeTickDomain> marketTradeTickList = tradeHistory.getData();
        double buySum = 0;//
        double buyAmount = 0;
        double sellSum = 0;
        double sellAmount = 0;
        int buyCount = 0;
        int sellCount = 0;
        for (MarketTradeTickDomain marketTradeTick : marketTradeTickList) {
            List<MarketTradeDomain> marketTradeList = marketTradeTick.getData();
            for (MarketTradeDomain marketTradeDomain : marketTradeList) {
                String direction = marketTradeDomain.getDirection();
                double amount = marketTradeDomain.getAmount();
                double price = marketTradeDomain.getPrice();
                if (direction.equals("buy")) {
                    ++buyCount;
                    buySum += amount * price;
                    buyAmount += amount;
                }
                if (direction.equals("sell")) {
                    ++sellCount;
                    sellSum += amount * price;
                    sellAmount += amount;
                }
//                System.out.println(marketTradeDomain);

            }
        }
        double buyMean = buySum / buyAmount;
        double sellMean = sellSum / sellAmount;
//        System.out.println("buy count:" + buyCount);
//        System.out.println("sell count:" + sellCount);
//        System.out.println("buy mean:" + buyMean);
//        System.out.println("sell mean:" + sellMean);
        thsd.setBuyCount(buyCount);
        thsd.setBuyMount(buyAmount);
        thsd.setBuyPrice(buyMean);
        thsd.setSellCount(sellCount);
        thsd.setSellMount(sellAmount);
        thsd.setSellPrice(sellMean);
        return thsd;


    }

    public static TradeDepthStatDomain getDepthMean(String symbol, String type) {
        TradeDepthStatDomain tdsd = new TradeDepthStatDomain();
        MarketDepthMainDomain tradeDepth = MarketDepth.getDepthData("zilusdt", "step0");
        if (tradeDepth == null) {
            tdsd.setStatus("network");
            return tdsd;
        } else if (tradeDepth.getStatus().equals("error")) {
            tdsd.setStatus("error");
            return tdsd;
        } else {
            tdsd.setStatus("ok");
        }
        tdsd.setTs(tradeDepth.getTs());
//        List asks1 = tradeDepth.getTick().getAsks();
//        for (int i = 0; i < asks1.size(); i++) {
//            System.out.println(asks1.get(i));
//        }
//        System.out.println("\n\n\n");
//        List bids1 = tradeDepth.getTick().getBids();
//        for (int i = 0; i < bids1.size(); i++) {
//            System.out.println(bids1.get(i));
//        }
        List<List> asks = tradeDepth.getTick().getAsks().subList(0, 50);
        double askSum = 0;// trade sum
        double askCount = 0;//trade amount
        for (int i = 0; i < asks.size(); i++) {
            //price*amount
            double price = Double.parseDouble(asks.get(i).get(0).toString());
            double amount = Double.parseDouble(asks.get(i).get(1).toString());
            double vol = price * amount * Math.pow(0.9, i);//0.9指数衰减。
            askSum += vol;
            askCount += amount;
        }
        //asks mean
        double askMean = askSum / askCount;

        List<List> bids = tradeDepth.getTick().getBids().subList(0, 50);
        double bidSum = 0;//trade amount
        double bidCount = 0;//trade amount
        for (int i = 0; i < bids.size(); i++) {
            //price*amount
            double price = Double.parseDouble(bids.get(i).get(0).toString());
            double amount = Double.parseDouble(bids.get(i).get(1).toString());
            double vol = price * amount * Math.pow(0.9, i);//0.9指数衰减。
            bidSum += vol;
            bidCount += amount;
        }
        //asks mean
        double bidMean = bidSum / bidCount;
//        System.out.println("bidMean:" + bidMean);
//        System.out.println("askMean:" + askMean);
        tdsd.setAskAmount(askCount);
        tdsd.setAskPrice(askMean);
        tdsd.setBidAmount(bidCount);
        tdsd.setBidPrice(bidMean);
        return tdsd;
    }

}
