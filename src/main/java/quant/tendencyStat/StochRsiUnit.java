package quant.tendencyStat;

import com.tictactec.ta.lib.MAType;
import domain.MarketDomain;
import quant.constant.TendencySign;

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
    private static MAType mtK = MAType.Dema;
    private static MAType mtD = MAType.Dema;

    public static void getStochRsiValue(double[] input, double[] inputLow, double[] inputClose, int optInTimePeriod, int optInFastK_Period, int optInFastD_Period){
        double[] outFastK = new double[input.length];
        double[] outFastD = new double[input.length];
        core.stochRsi(0, input.length - 1, input, optInTimePeriod, optInFastK_Period, optInFastD_Period, mtK, begin, length, outFastK, outFastD);


    }





    public TendencySign getTendencySign(List<MarketDomain> marketDomainList) {


        return null;
    }
}
