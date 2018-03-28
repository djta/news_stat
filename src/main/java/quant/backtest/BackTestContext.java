package quant.backtest;

import domain.MarketDomain;
import jdbc.impl.MarketDaoImpl;

import java.util.List;

/**
 * Created by hzyuyongmao on 2018/3/28.
 *
 * @Description
 */
public class BackTestContext {
    public static void main(String args[]) {
        System.out.println("ok");
        MarketDaoImpl marketDao = new MarketDaoImpl();
        List<MarketDomain> marketDomains = marketDao.getKlineData("btcusdt");
        for (MarketDomain md : marketDomains) {
            System.out.println(md);
        }
    }
}
