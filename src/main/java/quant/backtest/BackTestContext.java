package quant.backtest;

import domain.MarketDomain;
import jdbc.impl.MarketDaoImpl;
import quant.constant.TendencySign;
import quant.tendencyStat.TendencyContext;
import quant.trade.TradeContext;

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
//        for (MarketDomain md : marketDomains) {
//            System.out.println(md);
//        }
        TradeContext bc = new TradeContext(100000);
        for (int i = 0; i < marketDomains.size() - 250; i++) {
            List<MarketDomain> list = marketDomains.subList(i, i + 250);
            int result = predictTendency(list);
            if (result >= 2) {
                double close = list.get(list.size() - 1).getClose();
                boolean flag = bc.buy(close);
                if (flag) {
                    System.out.println("buy:" + list.get(list.size() - 1));
                }
            } else if (result <= -1) {
                double close = list.get(list.size() - 1).getClose();
                boolean flag = bc.sell(close);
                if (flag) {
                    System.out.println("sell:" + list.get(list.size() - 1));
                }

            }
        }
        bc.sell(marketDomains.get(marketDomains.size() - 1).getClose());
        System.out.println(marketDomains.get(marketDomains.size() - 1));
        System.out.println("result:" + bc);


    }

    public static void predict(List<MarketDomain> marketDomains) {
        TendencySign ts = TendencyContext.dmaSign(marketDomains, 2, 20, 5);
        TendencySign ts1 = TendencyContext.macdSign(marketDomains, 12, 35, 15);
        TendencySign ts2 = TendencyContext.maSign(marketDomains, 8, 66);
        TendencySign ts3 = TendencyContext.trixSign(marketDomains, 24, 166);
        if (ts.value + ts1.value + ts2.value + ts3.value >= 3) {
            System.out.println("bull");
        } else if (ts.value + ts1.value + ts2.value + ts3.value <= -3) {
            System.out.println("Bear");
        } else {
            System.out.println("Hold");
        }
    }

    public static int predictTendency(List<MarketDomain> marketDomains) {
        int result = TendencyContext.dmaSign(marketDomains, 2, 20, 5).value
                + TendencyContext.macdSign(marketDomains, 12, 35, 15).value
                + TendencyContext.maSign(marketDomains, 8, 66).value
                + TendencyContext.trixSign(marketDomains, 24, 166).value;
        return result;
    }

}
