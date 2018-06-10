package quant.tendencyStat;

import com.tictactec.ta.lib.MAType;
import domain.MarketDomain;
import domain.talib.StochDomain;
import quant.constant.TendencySign;
import talib.DataFormatTransformUtil;
import util.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzyuyongmao on 2018/5/7.
 *
 * @Description 【随机强弱指数STOCH RSI】 是非常蓍名的指标。同时也是〖波浪理论分析软件（Advanced GET）〗自带的指标之一。
 * 这指标的重点是用于波幅分析。特别适用于震荡市（不适用于趋势市）。
 * 它的特点是：（1）可以清楚地反映市场的“超买超卖”；
 * （2）可以看到指标与价格之间的背离；
 * （3）有清晰的买入和卖出信号。它与趋势类指标，如【MACD夹心】指标，【OSC震荡器】指标等配合使用时，效果非常显著。
 */
public class StochRsiUnit extends TendencyUnit {
    private static MAType mt = MAType.Sma;

    private int optInTimePeriod;
    private int optInFastK_Period;
    private int optInFastD_Period;

    public StochRsiUnit(int optInTimePeriod, int optInFastK_Period, int optInFastD_Period) {
        this.optInTimePeriod = optInTimePeriod;
        this.optInFastK_Period = optInFastK_Period;
        this.optInFastD_Period = optInFastD_Period;
    }

    public static List<StochDomain> getStochRsiValue(double[] input, int optInTimePeriod, int optInFastK_Period, int optInFastD_Period) {
        double[] outFastK = new double[input.length];
        double[] outFastD = new double[input.length];
        core.stochRsi(0, input.length - 1, input, optInTimePeriod, optInFastK_Period, optInFastD_Period, mt, begin, length, outFastK, outFastD);

        List<Double> outputKlist = DataFormatTransformUtil.result2List(outFastK);
        List<Double> outputDlist = DataFormatTransformUtil.result2List(outFastD);
        List<StochDomain> stochRsiDomains = new ArrayList<StochDomain>();
        for (int i = 0; i < outputDlist.size(); i++) {
            StochDomain stochRsiDomain = new StochDomain();
            stochRsiDomain.setSlowK(outputKlist.get(i));
            stochRsiDomain.setSlowD(outputDlist.get(i));
            stochRsiDomains.add(stochRsiDomain);
        }
        return stochRsiDomains;

    }


    public TendencySign getTendencySign(List<MarketDomain> marketDomainList) {
        double[] input = DataFormatTransformUtil.marketDomainlist2Array(marketDomainList);
        List<StochDomain> stochDomains = getStochRsiValue(input, optInTimePeriod, optInFastK_Period, optInFastD_Period);
        StochDomain stochDomain = stochDomains.get(stochDomains.size() - 1);
        System.out.println("stoch Ris:" + DateUtil.ts2DateStr(String.valueOf(marketDomainList.get(marketDomainList.size() - 1).getId())) + "\t" + stochDomain);

        return TendencySign.WAIT;
    }


    public static void main(String args[]) {
        double[] high = {21109.350, 2109.350, 2109.350, 2109.350, 2071.250, 3209.960, 207.6350, 2025.160, 1818.170, 1686.020};
        double[] low = {21019.350, 2109.350, 1230, 2209.350, 20371.250, 2049.9460, 2107.650, 2065.160, 1838.170, 1826.020};
        double[] close = {21109.350, 2109.350, 2109.350, 2029.350, 2037.250, 2509.960, 207.650, 2405.160, 1828.170, 1586.020};
        double[] outputD = new double[high.length];
        double[] outputk = new double[high.length];
        core.stochRsi(0, high.length - 1, close, 3, 2, 2, mt, begin, length, outputk, outputD);
        for (int i = 0; i < high.length; i++) {
            System.out.print(outputD[i] + "\t");
        }
        System.out.println();
        for (int i = 0; i < high.length; i++) {
            System.out.print(outputk[i] + "\t");
        }

    }

}
