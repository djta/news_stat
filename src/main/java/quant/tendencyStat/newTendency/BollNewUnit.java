package quant.tendencyStat.newTendency;

import domain.MarketDomain;
import domain.talib.BollingerBandDomain;
import quant.constant.TendencySign;
import quant.onlinebacktest.BollingerBandUnitOnline;
import quant.tendencyStat.TendencyUnit;
import talib.DataFormatTransformUtil;

import java.util.List;

/**
 * Created by hzyuyongmao on 2018/6/28.
 *
 * @Description
 */
public class BollNewUnit extends TendencyUnit {
    private int period;

    public BollNewUnit(int period) {
        this.period = period;
    }

    public TendencySign getTendencySign(List<MarketDomain> marketDomainList) {
        double[] input = DataFormatTransformUtil.marketDomainlist2Array(marketDomainList);
        List<BollingerBandDomain> bollingerBandDomains = BollingerBandUnitOnline.bollingerBands(input, period);
        int bollingSize = bollingerBandDomains.size();
        int marketSize = marketDomainList.size();
        double close = marketDomainList.get(marketSize - 1).getClose();
        double upper = bollingerBandDomains.get(bollingSize - 1).getUpper();
        double lower = bollingerBandDomains.get(bollingSize - 1).getLower();
        double mid = bollingerBandDomains.get(bollingSize - 1).getMid();
        double diff = (upper - lower) / mid;
        if (upper - lower <= 0) {
            return TendencySign.WAIT;
        }
        if ((upper - lower) / mid < 0.01) {
            return TendencySign.WAIT;
        }
        if (marketDomainList.get(marketSize - 2).getClose() >= upper
                && close < upper
                ) {
            return TendencySign.BEAR;

        } else if (marketDomainList.get(marketSize - 2).getClose() <= lower && close > lower
                ) {
            return TendencySign.BULL;
        }


        return TendencySign.WAIT;
    }
}
