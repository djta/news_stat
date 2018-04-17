package quant.tendencyStat;

import domain.MarketDomain;
import domain.talib.BollingerBandDomain;
import quant.constant.TendencySign;
import talib.DataFormatTransformUtil;

import java.util.List;

/**
 * Created by hzyuyongmao on 2018/4/17.
 *
 * @Description
 */
public class VBollingBandUnit extends TendencyUnit {

    private int period;

    public VBollingBandUnit(int period) {
        this.period = period;
    }

    public TendencySign getTendencySign(List<MarketDomain> marketDomainList) {
        double[] input = DataFormatTransformUtil.marketDomainlist2ArrayAmount(marketDomainList);
        List<BollingerBandDomain> bollingerBandDomains = BollingerBandUnit.bollingerBands(input, period);
        int bollingSize = bollingerBandDomains.size();
        int marketSize = marketDomainList.size();
        if (marketDomainList.get(marketSize - 1).getClose() > bollingerBandDomains.get(bollingSize - 1).getUpper()) {
            return TendencySign.BEAR;
        } else if (marketDomainList.get(marketSize - 1).getClose() <= bollingerBandDomains.get(bollingSize - 1).getLower()) {
            return TendencySign.BULL;
        }

        return TendencySign.WAIT;
    }
}
