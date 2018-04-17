package quant.tendencyStat;

import domain.MarketDomain;
import quant.constant.TendencySign;
import talib.DataFormatTransformUtil;

import java.util.List;

/**
 * Created by hzyuyongmao on 2018/4/17.
 *
 * @Description
 */
public class VRsiUnit extends TendencyUnit {
    private int period;

    public VRsiUnit(int period) {
        this.period = period;
    }


    public TendencySign getTendencySign(List<MarketDomain> marketDomainList) {
        double[] input = DataFormatTransformUtil.marketDomainlist2ArrayAmount(marketDomainList);
        List<Double> rsiList = RSIUnit.getRSIUnit(input, period);
        int size = rsiList.size();
        if (rsiList.get(size - 1) >= 60) {
            return TendencySign.BEAR;
        } else if (rsiList.get(size - 1) <= 40) {
            return TendencySign.BULL;
        }
        return TendencySign.WAIT;
    }
}
