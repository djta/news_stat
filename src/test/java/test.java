import com.alibaba.fastjson.JSON;
import domain.MarketDomain;
import domain.MarketMainDomain;
import jdbc.impl.MarketDaoImpl;
import util.HttpUtil;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by hzyuyongmao on 2018/2/23.
 *
 * @Description
 */
public class test {
    public static void main(String args[]) {
//        String result = HttpUtil.doGetMarket("btcusdt", "1min", 2000);
//        MarketMainDomain mmd = JSON.parseObject(result, MarketMainDomain.class);
//        for (MarketDomain md : mmd.getData()) {
//            md.setSymbol("btcusdt");
//        }
//        MarketDaoImpl mdImpl = new MarketDaoImpl();
//        for (MarketDomain md : mmd.getData()) {
//            System.out.println(md.toString());
//            mdImpl.insertMarket(md);
//        }
        BigDecimal bd = new BigDecimal("30283784251848204425");

    }
}
