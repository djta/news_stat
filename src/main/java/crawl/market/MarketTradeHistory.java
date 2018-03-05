package crawl.market;

import com.alibaba.fastjson.JSON;
import domain.MarketTradeDomain;
import domain.MarketTradeHistoryMainDomain;
import domain.MarketTradeMainDomain;
import domain.MarketTradeTickDomain;
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
        //0-2000
        long tsSend = System.currentTimeMillis();
        String result = HttpUtil.doGetData(Constants.URL_TRADE_HISTORY + "symbol=zilusdt&size=100");
        System.out.println(result);
        MarketTradeHistoryMainDomain mtd = JSON.parseObject(result, MarketTradeHistoryMainDomain.class);
        List<MarketTradeTickDomain> list = mtd.getData();
        double sellAmount = 0;
        double sellSum = 0;
        int sellCount = 0;//sell count
        double buyAmount = 0;
        double buySum = 0;
        int buyCount = 0;//buy count
        long ts = mtd.getTs();
        System.out.println(tsSend - ts);
        for (MarketTradeTickDomain mds : list) {
            for (MarketTradeDomain md : mds.getData()) {
                System.out.println(md.toString());
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
        System.out.println("buy mean:" + buyMean + "\tbuy amount:" + buyAmount + "\tbuy count:" + buyCount);
        System.out.println("sell mean:" + sellMean + "\tsell amount:" + sellAmount + "\tsell count:" + sellCount);

    }
}
