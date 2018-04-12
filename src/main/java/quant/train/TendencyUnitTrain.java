package quant.train;

import domain.MarketDomain;
import jdbc.impl.MarketDaoImpl;
import quant.tendencyStat.BollingerBandUnit;
import quant.tendencyStat.TendencyContext;
import quant.tendencyStat.TendencyUnit;
import quant.trade.TradeContext;
import quant.trade.TradeDomain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static quant.backtest.BackTestContext.backTest;

/**
 * Created by hzyuyongmao on 2018/4/12.
 *
 * @Description
 */
public class TendencyUnitTrain {
    public static void main(String args[]) {
        MarketDaoImpl marketDao = new MarketDaoImpl();
        List<TrainDomain> profList = new ArrayList<TrainDomain>();
        for (int period = 1; period < 200; period++) {
            List<MarketDomain> marketDomains = marketDao.getKlineData("zilusdt");
            List<TendencyUnit> tendencyUnits = new ArrayList<TendencyUnit>();
            TradeContext bc = new TradeContext(100000);
            tendencyUnits.add(new BollingerBandUnit(period));//反趋势
            TendencyContext tc = new TendencyContext(0.2, 0.6, tendencyUnits);
            for (int i = 0; i < marketDomains.size() - 250; i++) {
                List<MarketDomain> list = marketDomains.subList(i, i + 250);
                backTest(list, tc, bc);
            }
            bc.resultStat();
            TrainDomain trainDomain = new TrainDomain();
            trainDomain.setPeriod(period);
            trainDomain.setFailCount(bc.getFailCount());
            trainDomain.setWinCount(bc.getWinCount());
            trainDomain.setFund(bc.getFund());
            trainDomain.setWinsRate(bc.getWinsRate());
            trainDomain.setWinToLossRate(bc.getWinToLossRate());
            trainDomain.setCost(bc.getCost());
            profList.add(trainDomain);
        }
        Collections.sort(profList, new TrainDomainComp());
        for (TrainDomain td : profList) {
            System.out.println(td);
        }

    }

    static class TrainDomainComp implements Comparator<Object> {

        public int compare(Object o1, Object o2) {
            TrainDomain p1 = (TrainDomain) o1;
            TrainDomain p2 = (TrainDomain) o2;
            return p2.getFund() > p1.getFund() ? 1 : -1;
        }
    }
}
