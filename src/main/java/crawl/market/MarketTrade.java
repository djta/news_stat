package crawl.market;

import com.alibaba.fastjson.JSON;
import crawl.common.Symbols;
import domain.MarketTradeDomain;
import domain.MarketTradeMainDomain;
import util.Constants;
import util.HttpUtil;

import java.util.List;

/**
 * Created by hzyuyongmao on 2018/2/26.
 *
 * @Description
 */
public class MarketTrade {
    public static void main(String args[]) {

        long ts = System.currentTimeMillis();
        String result = HttpUtil.doGetData(Constants.URL_TRADE + "symbol=zilusdt");
        System.out.println(result);
        MarketTradeMainDomain mtd = JSON.parseObject(result, MarketTradeMainDomain.class);
        if (mtd.getStatus().equals("error")) {
            System.out.println("error");
        }
        long tsMtd = mtd.getTs();
        long diff = ts - tsMtd;
        System.out.println("ts:" + diff);
        List<MarketTradeDomain> list = mtd.getTick().getData();
        for (MarketTradeDomain md : list) {
            System.out.println(md);

        }
    }
}
