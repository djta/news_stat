package quant.tendencyStat;

import domain.MarketDomain;
import quant.constant.TendencySign;
import talib.DataFormatTransformUtil;

import java.util.List;

/**
 * Created by 范志伟 on 2018-04-11.
 * 横盘状态下，RSI会变得无用（可以算一下波动率或方差之类的，筛选出高波动性的币）
 */
public class RSIUnit extends TendencyUnit {
    public static void main(String args[]) {
        double[] array = {207.650, 205.160, 210.870, 209.350, 207.250, 209.960, 207.650, 205.160, 188.170, 186.020};
        double[] output = new double[array.length];
        core.rsi(0, array.length - 1, array, 5, begin, length, output);
        for (int i = 0; i < array.length - 1; i++) {
            System.out.print(output[i] + "\t");
        }
    }

    public static void getRSIUnit(double[] input, int period) {
        double[] output = new double[input.length];
        core.rsi(0, input.length - 1, input, period, begin, length, output);
        List<Double> list = DataFormatTransformUtil.result2List(output);
        
    }


    public TendencySign getTendencySign(List<MarketDomain> marketDomainList) {
        return null;
    }
}
