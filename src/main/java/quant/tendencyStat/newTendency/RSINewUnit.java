package quant.tendencyStat.newTendency;

import domain.MarketDomain;
import quant.constant.TendencySign;
import quant.tendencyStat.RSIUnit;
import quant.tendencyStat.TendencyUnit;

import java.util.List;

/**
 * Created by hzyuyongmao on 2018/6/26.
 *
 * @Description
 */
public class RSINewUnit extends TendencyUnit {
    private int shortPeriod;
    private int longPeriod;

    public RSINewUnit(int shortPeriod, int longPeriod) {
        this.shortPeriod = shortPeriod;
        this.longPeriod = longPeriod;
    }

    public TendencySign getTendencySign(List<MarketDomain> marketDomainList) {

        List<Double> shortList = RSIUnit.getRSIUnit(marketDomainList, shortPeriod);
        List<Double> longList = RSIUnit.getRSIUnit(marketDomainList, longPeriod);
        int size = shortList.size();
        if (shortList.get(size - 1) <= 20 && longList.get(size - 1) <= 20
                && shortList.get(size - 1) > longList.get(size - 1)
                && shortList.get(size - 2) < longList.get(size - 2)) {
            return TendencySign.BULL;
        }
        if (shortList.get(size - 1) >= 80 && longList.get(size - 1) >= 80
                && shortList.get(size - 1) < longList.get(size - 1)
                && shortList.get(size - 2) > longList.get(size - 2)) {
            return TendencySign.BEAR;
        }

        return TendencySign.WAIT;
    }
}
