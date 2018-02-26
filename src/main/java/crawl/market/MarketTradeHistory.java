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
        String result = HttpUtil.doGetData(Constants.URL_TRADE_HISTORY + "symbol=ethusdt&size=100");
        System.out.println(result);
        MarketTradeHistoryMainDomain mtd = JSON.parseObject(result, MarketTradeHistoryMainDomain.class);
        List<MarketTradeTickDomain> list = mtd.getData();
        for (MarketTradeTickDomain mds : list) {
            for (MarketTradeDomain md : mds.getData()) {
                System.out.println(md.toString());
            }
        }
    }
}
