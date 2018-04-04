package quant.backtest;

import domain.MarketDomain;
import jdbc.impl.MarketDaoImpl;
import quant.constant.TendencySign;
import quant.constant.TradeSign;
import quant.tendencyStat.*;
import quant.trade.TradeContext;

import java.util.ArrayList;
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
        //Units
        List<TendencyUnit> tendencyUnits = new ArrayList<TendencyUnit>();
        tendencyUnits.add(new TrixUnit(3, 10));
        tendencyUnits.add(new MaUnit(3, 10));
        tendencyUnits.add(new MacdUnit(3, 10, 7));
        tendencyUnits.add(new DMAUnit(3, 20, 5));
        TendencyContext tc = new TendencyContext(0.2, 0.7, tendencyUnits);
        //
        TradeContext bc = new TradeContext(100000);

        for (int i = 0; i < marketDomains.size() - 250; i++) {

            List<MarketDomain> list = marketDomains.subList(i, i + 250);
//            int result = predictTendency(list);
//            if (result >= 3) {
//                double close = list.get(list.size() - 1).getClose();
//                boolean flag = bc.buy(close);
//                if (flag) {
//                    System.out.println("buy:" + list.get(list.size() - 1));
//                }
//            } else if (result <= -1) {
//                double close = list.get(list.size() - 1).getClose();
//                boolean flag = bc.sell(close);
//                if (flag) {
//                    System.out.println("sell:" + list.get(list.size() - 1));
//                }
//
//            }

            backTest(list, tc, bc);
        }
        bc.sell(bc.getBuyPrice());
//        System.out.println(marketDomains.get(marketDomains.size() - 1));
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
        int result = TendencyContext.dmaSign(marketDomains, 3, 20, 5).value
                + TendencyContext.macdSign(marketDomains, 3, 10, 7).value
                + TendencyContext.maSign(marketDomains, 3, 10).value
                + TendencyContext.trixSign(marketDomains, 3, 10).value;
        return result;
    }

    public static void backTest(List<MarketDomain> marketDomains, TendencyContext tendencyContext, TradeContext tradeContext) {
        TradeSign tradeSign = tendencyContext.getTradeSign(marketDomains);
        double close = marketDomains.get(marketDomains.size() - 1).getClose();
        if (tradeSign.equals(TradeSign.OPEN)) {
            tradeContext.buy(close);
        }
        //跟踪止损
        tradeContext.stopProfit(close);
        if (tradeSign.equals(TradeSign.CLOSE)) {
            tradeContext.sell(close);
        }
    }

}