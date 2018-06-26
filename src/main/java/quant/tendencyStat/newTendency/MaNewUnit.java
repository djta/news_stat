package quant.tendencyStat.newTendency;

import domain.MarketDomain;
import quant.constant.TendencySign;
import quant.tendencyStat.MaUnit;
import quant.tendencyStat.TendencyUnit;
import quant.tendencyStat.otherStat.KlineSlopeStat;
import util.LimitQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzyuyongmao on 2018/6/25.
 *
 * @Description
 */
public class MaNewUnit extends TendencyUnit {
    private int shortPeriod;
    private int middlePeriod;
    private int longPeriod;

    public MaNewUnit(int shortPeriod, int middlePeriod, int longPeriod) {
        this.shortPeriod = shortPeriod;
        this.middlePeriod = middlePeriod;
        this.longPeriod = longPeriod;
    }

    List<TendencySign> tendencyList = new ArrayList<TendencySign>(100);

    public TendencySign getTendencySign(List<MarketDomain> marketDomainList) {
        List<Double> shortList = MaUnit.sma(marketDomainList, shortPeriod);
        List<Double> middleList = MaUnit.sma(marketDomainList, middlePeriod);
        List<Double> longList = MaUnit.sma(marketDomainList, longPeriod);
        //lineage slope
        List<Double> shortSlopeList = KlineSlopeStat.getKlineLinearRegSlope(marketDomainList, shortPeriod);
        List<Double> middleSlopeList = KlineSlopeStat.getKlineLinearRegSlope(marketDomainList, middlePeriod);
        List<Double> longSlopeList = KlineSlopeStat.getKlineLinearRegSlope(marketDomainList, longPeriod);
        int size = shortList.size();
        if (shortList.get(size - 1) > middleList.get(size - 1)
                && shortList.get(size - 2) <= middleList.get(size - 2)
                && (shortSlopeList.get(size - 1) - middleSlopeList.get(size - 1)) > 0.12) {
            if (tendencyList.size() == 0 || (tendencyList.size() > 0 && tendencyList.get(0).equals(TendencySign.BEAR))) {
                tendencyList.clear();
                tendencyList.add(TendencySign.BULL);
            }

        }
        if (shortList.get(size - 1) > longList.get(size - 1)
                && shortList.get(size - 2) <= longList.get(size - 2)
                && (shortSlopeList.get(size - 1) - longSlopeList.get(size - 1)) > 0.15) {
            if (tendencyList.size() == 1 && tendencyList.get(0).equals(TendencySign.BULL)) {
                tendencyList.add(TendencySign.BULL);
            }
        }
        if (middleList.get(size - 1) > longList.get(size - 1)
                && middleList.get(size - 2) <= longList.get(size - 2)) {
            if (tendencyList.size() == 2 && tendencyList.get(0).equals(TendencySign.BULL)) {
                tendencyList.add(TendencySign.BULL);
            }
        }
        if (longSlopeList.get(size - 1) > 0
                && middleSlopeList.get(size - 1) > 0
                && shortSlopeList.get(size - 1) > 0) {
            if (tendencyList.size() == 3 && tendencyList.get(0).equals(TendencySign.BULL)) {
                tendencyList.add(TendencySign.BULL);
            }
        }
        //
        if (shortList.get(size - 1) <= middleList.get(size - 1)
                && shortList.get(size - 2) >= middleList.get(size - 2)) {
            if (tendencyList.size() == 0 || (tendencyList.size() > 0 && !tendencyList.get(0).equals(TendencySign.BULL))) {
                tendencyList.clear();
                tendencyList.add(TendencySign.BEAR);
            }
        }
        if (shortList.get(size - 1) <= longList.get(size - 1)
                && shortList.get(size - 2) > longList.get(size - 2)) {
            if (tendencyList.size() == 1 && tendencyList.get(0).equals(TendencySign.BEAR)) {
                tendencyList.add(TendencySign.BEAR);
            }

        }
        if (middleList.get(size - 1) <= longList.get(size - 1)
                && middleList.get(size - 2) > longList.get(size - 2)) {
            if (tendencyList.size() == 2 && tendencyList.get(1).equals(TendencySign.BEAR)) {
                tendencyList.add(TendencySign.BEAR);
            }
        }
//        if (longSlopeList.get(size - 1) < 0) {
//            if (tendencyList.size() == 3 && tendencyList.get(2).equals(TendencySign.BEAR)) {
//                tendencyList.add(TendencySign.BEAR);
//            }
//        }
//        System.out.println("limitQueue:" + tendencyList.size() + "\tlimitQueue Value:");
        if (tendencyList.size() == 4 && tendencyList.get(0).equals(TendencySign.BULL)) {
            tendencyList.clear();
            return TendencySign.BULL;
        } else if (tendencyList.size() >= 1 && tendencyList.get(0).equals(TendencySign.BEAR)) {
            tendencyList.clear();
            return TendencySign.BEAR;
        }
        return TendencySign.WAIT;

    }
}
