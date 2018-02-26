package crawl.market;

import com.alibaba.fastjson.JSON;
import domain.MarketDepthDomain;
import domain.MarketDepthMainDomain;
import domain.MarketMainDomain;
import util.Constants;
import util.HttpUtil;

import java.util.List;

/**
 * Created by hzyuyongmao on 2018/2/26.
 *
 * @Description
 */
public class MarketDepth {
    public static void main(String args[]) {
        String url = Constants.URL_MARKET_DEPTH + "symbol=ethusdt&type=step1";
        String result = HttpUtil.doGetData(url);
        MarketDepthMainDomain mmd = JSON.parseObject(result, MarketDepthMainDomain.class);
        List<List> asks = mmd.getTick().getAsks();
        for (List d : asks) {
            System.out.println(d.toString());
            System.out.println("price=" + d.get(0) + ",amount=" + d.get(1));
        }

    }
}
