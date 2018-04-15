package quant.onlinebacktest;

import com.tictactec.ta.lib.MAType;
import com.tictactec.ta.lib.MInteger;
import domain.MarketDomain;
import domain.talib.BollingerBandDomain;
import quant.constant.TendencySign;
import quant.tendencyStat.TendencyUnit;
import talib.DataFormatTransformUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BollingerBandUnitOnline extends TendencyUnit {
    private static MInteger begin = new MInteger();
    private static MInteger length = new MInteger();
    private static MAType maType = MAType.Kama;

    private int period;

    public BollingerBandUnitOnline(int period) {
        this.period = period;
    }

    public static void main(String args[]) {
        double[] array = {207.650, 205.160, 210.870, 209.350, 207.250, 209.960, 207.650, 205.160, 188.170, 186.020};
        List<BollingerBandDomain> list = bollingerBands(array, 2);
        System.out.println(list);
    }

    public static List<BollingerBandDomain> bollingerBands(double[] input, int period) {
        double[] upper = new double[input.length];
        double[] mid = new double[input.length];
        double[] lower = new double[input.length];
        core.bbands(0, input.length - 1, input, period, 2.2, 2.2, maType, begin, length, upper, mid, lower);

        List<Double> upperList = DataFormatTransformUtil.result2List(upper);
        List<Double> midList = DataFormatTransformUtil.result2List(mid);
        List<Double> lowerList = DataFormatTransformUtil.result2List(lower);
        List<BollingerBandDomain> bollingBandList = new ArrayList<BollingerBandDomain>();
        for (int i = 0; i < upperList.size(); i++) {
            BollingerBandDomain bbd = new BollingerBandDomain();
            bbd.setUpper(upperList.get(i));
            bbd.setMid(midList.get(i));
            bbd.setLower(lowerList.get(i));
            bollingBandList.add(bbd);

        }
        return bollingBandList;
    }

    /*
       bear，反趋势
       破上下轨，则反转。
       中上轨和中下轨则反转。
       考虑结合kdj，胜率高，但行情很微小。
       https://wenku.baidu.com/view/636229a11eb91a37f1115ce8.html
     */
    public TendencySign getTendencySign(List<MarketDomain> marketDomainList) {
        double[] input = DataFormatTransformUtil.marketDomainlist2Array(marketDomainList);
        List<BollingerBandDomain> bollingerBandDomains = bollingerBands(input, period);
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