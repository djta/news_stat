package quant.backtest;

import domain.MarketDomain;
import jdbc.impl.MarketDaoImpl;
import quant.constant.TendencySign;
import quant.tendencyStat.TendencyContext;

import java.util.List;

/**
 * Created by 范志伟 on 2018-03-30.
 */
public class Test {
    public static void main(String args[]) {
        System.out.println("OK");
        MarketDaoImpl marketDao = new MarketDaoImpl();
        List<MarketDomain> marketDomains = marketDao.getKlineData("btcusdt");
        BidContext bc = new BidContext(100000);
        for (int i = 0; i < marketDomains.size() - 500; i++) {
            List<MarketDomain> list = marketDomains.subList(i, i + 500);
            TendencySign sign = TendencyContext.macdSign(list, 12, 35,15);
            if (sign.value == 1) {
                double close = list.get(list.size() - 1).getClose();
                bc.buy(close);

            } else if (sign.value == -1) {
                double close = list.get(list.size() - 1).getClose();
                bc.sell(close);
            }
        }
        bc.sell(marketDomains.get(marketDomains.size() - 501).getClose());
        System.out.println(bc);
    }
}
