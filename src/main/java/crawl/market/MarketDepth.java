package crawl.market;

import com.alibaba.fastjson.JSON;
import domain.MarketDepthDomain;
import domain.MarketDepthMainDomain;
import domain.MarketMainDomain;
import domain.stat.TradeDepthStatDomain;
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
            long ts = System.currentTimeMillis();
            TradeDepthStatDomain tdsd = getDepth("zilusdt", "step0");
            System.out.println(tdsd);
            System.out.println(ts - tdsd.getTs());
            Thread.sleep(500);
        }

    }

    public static TradeDepthStatDomain getDepth(String symbol, String type) {
        TradeDepthStatDomain tdsd = new TradeDepthStatDomain();

        String url = Constants.URL_MARKET_DEPTH + "symbol=" + symbol + "&type=" + type;
        String result = HttpUtil.doGetData(url);
        System.out.println(result);
        tdsd.setSymbol(symbol);
        if (result == null) {
            tdsd.setStatus("network");
            return tdsd;
        }
        MarketDepthMainDomain mmd = JSON.parseObject(result, MarketDepthMainDomain.class);
        tdsd.setTs(mmd.getTs());
        if (mmd.getStatus().equals("error")) {
            tdsd.setStatus("error");
            return tdsd;
        }
        tdsd.setStatus("ok");
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
        tdsd.setAskAmount(asksSum);
        tdsd.setAskPrice(asksMean);
        tdsd.setBidAmount(bidsSum);
        tdsd.setBidPrice(bidsMean);
        return tdsd;

    }
}
