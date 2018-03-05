package crawl.market;

import com.alibaba.fastjson.JSON;
import domain.MarketTradeDomain;
import domain.MarketTradeHistoryMainDomain;
import domain.MarketTradeMainDomain;
import domain.MarketTradeTickDomain;
import domain.stat.TradeHistoryStatDomain;
import util.Constants;
import util.HttpUtil;

import java.util.List;

/**
 * Created by hzyuyongmao on 2018/2/26.
 *
 * @Description
 */
public class MarketTradeHistory {
    public static void main(String args[]) {

//        TradeHistoryStatDomain mmd=getTradeHistoryStat("elausdt",100);
//        System.out.println(mmd);
        while (true){
            TradeHistoryStatDomain mmd=getTradeHistoryStat("elausdt",100);
            System.out.println(mmd);

        }

    }
    public static TradeHistoryStatDomain getTradeHistoryStat(String symbol,int size){
        //0-2000
        TradeHistoryStatDomain thsDoamin=new TradeHistoryStatDomain();
        String result = HttpUtil.doGetData(Constants.URL_TRADE_HISTORY + "symbol="+symbol+"&size="+size);
        thsDoamin.setSymbol(symbol);
        if(result==null){
            thsDoamin.setStatus("network");//网络出错
            return thsDoamin;
        }
        MarketTradeHistoryMainDomain mtd = JSON.parseObject(result, MarketTradeHistoryMainDomain.class);
        thsDoamin.setTs(mtd.getTs());
        if(mtd.getStatus().equals("error")){
            thsDoamin.setStatus("error");//信息返回出错
            return thsDoamin;
        }
        thsDoamin.setStatus("ok");
        List<MarketTradeTickDomain> list = mtd.getData();
        double sellAmount = 0;
        double sellSum = 0;
        int sellCount = 0;//sell count
        double buyAmount = 0;
        double buySum = 0;
        int buyCount = 0;//buy count
        long ts = mtd.getTs();
        for (MarketTradeTickDomain mds : list) {
            for (MarketTradeDomain md : mds.getData()) {
                String direct = md.getDirection();
                double amount = md.getAmount();
                double price = md.getPrice();
                if (direct.equals("buy")) {
                    buyAmount += amount;
                    buySum = buySum + (price * amount);
                    buyCount++;
                } else if (direct.equals("sell")) {
                    sellAmount += amount;
                    sellSum = sellSum + (price * amount);
                    sellCount++;
                } else {
                    continue;
                }
            }
        }
        double sellMean = sellSum / sellAmount;
        double buyMean = buySum / buyAmount;
        thsDoamin.setBuyCount(buyCount);
        thsDoamin.setBuyPrice(buyMean);
        thsDoamin.setBuyMount(buyAmount);
        thsDoamin.setSellCount(sellCount);
        thsDoamin.setSellMount(sellAmount);
        thsDoamin.setSellPrice(sellMean);
        return thsDoamin;
    }
}
