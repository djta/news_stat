package quant.tendencyStat;

import com.tictactec.ta.lib.MAType;
import com.tictactec.ta.lib.MInteger;
import domain.MarketDomain;
import domain.talib.BollingerBandDomain;
import quant.constant.TendencySign;
import quant.onlinebacktest.BollingerBandUnitOnline;
import talib.DataFormatTransformUtil;

import java.util.List;

public class MixTendencyUnit extends TendencyUnit {

    private int bollPeriod;
    private int shortPeriod;
    private int longPeriod;

    public MixTendencyUnit(int bollPeriod, int shortPeriod, int longPeriod) {
        this.bollPeriod = bollPeriod;
        this.shortPeriod = shortPeriod;
        this.longPeriod = longPeriod;
    }


    public TendencySign getTendencySign(List<MarketDomain> marketDomainList) {
        double[] input = DataFormatTransformUtil.marketDomainlist2Array(marketDomainList);
        List<BollingerBandDomain> bollingerBandDomains = BollingerBandUnitOnline.bollingerBands(input, bollPeriod);
        int bollingSize = bollingerBandDomains.size();
        int marketSize = marketDomainList.size();
        double close = marketDomainList.get(marketSize - 1).getClose();
        double upper = bollingerBandDomains.get(bollingSize - 1).getUpper();
        double lower = bollingerBandDomains.get(bollingSize - 1).getLower();
        double mid = bollingerBandDomains.get(bollingSize - 1).getMid();
        if (upper - lower <= 0) {//布林带缩位震荡，去除虚假点击
            return TendencySign.WAIT;
        }
        List<Double> sma = MaUnit.sma(marketDomainList, shortPeriod);
        List<Double> lma = MaUnit.sma(marketDomainList, longPeriod);
        int smaSize = sma.size();
        int lmaSize = lma.size();

        if (close > upper && sma.get(smaSize - 1) < lma.get(lmaSize - 1) && sma.get(smaSize - 2) > lma.get(lmaSize - 2)) {//bear
            return TendencySign.BEAR;
        }
        if (close < lower && sma.get(smaSize - 1) > sma.get(smaSize - 2) && sma.get(smaSize - 1) > lma.get(lmaSize - 1) && sma.get(smaSize - 2) < lma.get(lmaSize - 2)) {
            return TendencySign.BULL;
        }
        return TendencySign.WAIT;
    }
}
