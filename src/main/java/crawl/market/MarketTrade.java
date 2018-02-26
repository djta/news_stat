package crawl.market;

import com.alibaba.fastjson.JSON;
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
        String result = HttpUtil.doGetData(Constants.URL_TRADE + "symbol=ethusdt");
        System.out.println(result);
        MarketTradeMainDomain mtd = JSON.parseObject(result, MarketTradeMainDomain.class);
        List<MarketTradeDomain> list = mtd.getTick().getData();
        for (MarketTradeDomain md : list) {
            System.out.println(md);
        }
    }
}
