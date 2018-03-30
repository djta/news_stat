package quant.backtest;

import domain.MarketDomain;
import jdbc.impl.MarketDaoImpl;
import quant.constant.TendencySign;
import quant.tendencyStat.TendencyContext;
import quant.trade.TradeContext;

import java.util.List;

/**
 * Created by 范志伟 on 2018-03-30.
 */
public class Test {
    public static void main(String args[]) {
        System.out.println("OK");
        MarketDaoImpl marketDao = new MarketDaoImpl();
        List<MarketDomain> marketDomains = marketDao.getKlineData("btcusdt");
        TradeContext bc = new TradeContext(100000);
        for (int i = 0; i < marketDomains.size() - 250; i++) {
            List<MarketDomain> list = marketDomains.subList(i, i + 250);
//            TendencySign sign = TendencyContext.macdSign(list, 12, 35, 15);
//            TendencySign sign = TendencyContext.kamaSign(list, 5, 8, 20);
            TendencySign sign = TendencyContext.maSign(list, 25, 60);
            double close = list.get(list.size() - 1).getClose();
//             bc.stoplossUnit(close,1.02);
            if (sign.value == 1) {
                bc.buy(close);

            } else if (sign.value == -1) {
                boolean flag = bc.sell(close);
                if (flag) {
                    System.out.println("operation:" + bc);
                }

            }
        }
        bc.sell(bc.getBuyPrice());
        System.out.println(bc);
    }

    public void testUnit(List<MarketDomain> marketDomains, TradeContext bc) {
        for (int i = 0; i < marketDomains.size() - 250; i++) {
            List<MarketDomain> list = marketDomains.subList(i, i + 250);
            int resultSign = BackTestContext.predictTendency(list);

        }

    }


}
