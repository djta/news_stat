package quant.backtest;

import domain.MarketDomain;
import jdbc.impl.MarketDaoImpl;
import quant.constant.TendencySign;
import quant.tendencyStat.TendencyContext;

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
        BidContext bc = new BidContext(10000);
        for (int i = 0; i < marketDomains.size() - 50; i++) {
            List<MarketDomain> list = marketDomains.subList(i, i + 50);
            int result = predictTendency(list);
            if (result >= 3) {
                System.out.println(list.get(list.size() - 1));
                double colse = list.get(list.size() - 1).getClose();
                System.out.println("买入：" + colse);
                bc.buy(colse);
            } else if (result <= -3) {
                double close = list.get(list.size() - 1).getClose();
                bc.sell(close);
                System.out.println(list.get(list.size() - 1));
                System.out.println("卖出：" + close);
            }
        }
        bc.sell(marketDomains.get(marketDomains.size() - 1).getClose());
        System.out.println("result:" + bc);


    }

    public static void predict(List<MarketDomain> marketDomains) {
        TendencySign ts = TendencyContext.dmaSign(marketDomains, 2, 20, 5);
        TendencySign ts1 = TendencyContext.macdSign(marketDomains, 2, 20, 5);
        TendencySign ts2 = TendencyContext.maSign(marketDomains, 2, 20);
        TendencySign ts3 = TendencyContext.trixSign(marketDomains, 2, 20);
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
                + TendencyContext.macdSign(marketDomains, 2, 20, 5).value
                + TendencyContext.maSign(marketDomains, 2, 20).value + TendencyContext.trixSign(marketDomains, 2, 20).value;
        return result;
    }
}
