package quant.backtest;

import domain.MarketDomain;
import jdbc.impl.MarketDaoImpl;
import quant.constant.TendencySign;
import quant.tendencyStat.TendencyContext;
import quant.trade.TradeContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by 范志伟 on 2018-03-30.
 */
public class TendencyParamTrain2 {
    public static void main(String args[]) {
        MarketDaoImpl marketDao = new MarketDaoImpl();
        List<MarketDomain> marketDomains = marketDao.getKlineData("btcusdt");
        List<TrainDomain> trainDomains = train(marketDomains);
        Collections.sort(trainDomains, new TendencyParamTrain.ProfitComparator());
        for (int i = 0; i < 50; i++) {
            System.out.println(trainDomains.get(i));
        }
    }

    public static List<TrainDomain> train(List<MarketDomain> marketDomains) {
        int shortPeriod;
        int longPeriod;
        int mid;
        List<TrainDomain> trainDomains = new ArrayList<TrainDomain>();
        for (int i = 1; i < 50; i++) {
            shortPeriod = i;
            for (int j = 1; j < 200; j++) {
                longPeriod = j;
                for (int k = i; k < j; k++) {
                    mid = k;
                    TrainDomain td = new TrainDomain();
                    System.out.println("short:" + i + ",long:" + j);
                    TradeContext profit = bidAndAsk(marketDomains, shortPeriod, longPeriod, mid);
                    if (profit.getBuy() == 0 || profit.getSell() == 0) {
                        continue;
                    }
                    td.setProfit(profit.profit());
                    td.setShortPeriod(shortPeriod);
                    td.setLongPeriod(longPeriod);
                    td.setBuy(profit.getBuy());
                    td.setSell(profit.getSell());
                    trainDomains.add(td);
                }


            }
        }

        return trainDomains;


    }

    public static TradeContext bidAndAsk(List<MarketDomain> marketDomains, int shortPeriod, int longPeriod, int mid) {
        TradeContext bc = new TradeContext(100000);
        for (int i = 0; i < marketDomains.size() - 500; i++) {
            List<MarketDomain> list = marketDomains.subList(i, i + 500);
            TendencySign sign = TendencyContext.macdSign(list, shortPeriod, longPeriod, mid);
            if (sign.value == 1) {
                double close = list.get(list.size() - 1).getClose();
                bc.buy(close);

            } else if (sign.value == -1) {
                double close = list.get(list.size() - 1).getClose();
                bc.sell(close);
            }
        }
        bc.sell(marketDomains.get(marketDomains.size() - 501).getClose());
        return bc;

    }

    static class ProfitComparator implements Comparator<Object> {
        public int compare(Object object1, Object object2) {// 实现接口中的方法
            TrainDomain p1 = (TrainDomain) object1; // 强制转换
            TrainDomain p2 = (TrainDomain) object2;
            return p2.getProfit() > p1.getProfit() ? 1 : -1;
        }
    }
}