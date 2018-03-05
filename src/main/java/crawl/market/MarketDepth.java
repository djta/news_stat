package crawl.market;

import com.alibaba.fastjson.JSON;
import domain.MarketDepthDomain;
import domain.MarketDepthMainDomain;
import domain.MarketMainDomain;
import util.Constants;
import util.HttpUtil;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by hzyuyongmao on 2018/2/26.
 *
 * @Description
 */
public class MarketDepth {
    public static void main(String args[]) throws InterruptedException {
        while (true) {
            getDepth();
            Thread.sleep(500);
        }

    }

    public static void getDepth() {
        String url = Constants.URL_MARKET_DEPTH + "symbol=zilusdt&type=step1";
        String result = HttpUtil.doGetData(url);
        MarketDepthMainDomain mmd = JSON.parseObject(result, MarketDepthMainDomain.class);
//        System.out.println(JSON.toJSON(mmd));
        if (mmd.getStatus().equals("error")) {
            System.out.println("error");
        }
        //ask mean
        List<List> asks = mmd.getTick().getAsks();
//        double asksSum = 0;
        double asksSum = 0;
        double asksCount = 0;
        for (List ask : asks) {
            BigDecimal prise = new BigDecimal(ask.get(0).toString());
            BigDecimal count = new BigDecimal(ask.get(1).toString());
            prise = prise.multiply(count);
            asksSum += prise.doubleValue();
            asksCount += count.doubleValue();
        }
        double asksMean = asksSum / asksCount;
        //bids
        List<List> bids = mmd.getTick().getBids();
        double bidsSum = 0;
        double bidsCount = 0;
        for (List<BigDecimal> bid : bids) {
            BigDecimal prise = new BigDecimal(bid.get(0).toString());
            BigDecimal count = new BigDecimal(bid.get(1).toString());
            prise = prise.multiply(count);
            bidsSum += prise.doubleValue();
            bidsCount += count.doubleValue();
        }
        double bidsMean = bidsSum / bidsCount;
        System.out.println("asks mean：" + asksMean + "\tcount：" + asksSum);
        System.out.println("bids mean：" + bidsMean + "\tcount：" + bidsSum);
    }
}
