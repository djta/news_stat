package quant.tendencyStat;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MInteger;
import domain.MarketDomain;
import talib.DataFormatTransformUtil;

import java.util.List;

/**
 * Created by 范志伟 on 2018-03-26.
 * Kaufman Adaptive Moving Average
 */
public class KAMAUnit {
    private static Core core = new Core();
    private int lookback = 0;
    private static MInteger begin = new MInteger();
    private static MInteger length = new MInteger();

    public static void main(String args[]) {
        double[] array = {207.650, 205.160, 210.870, 209.350, 207.250, 209.960, 207.650, 205.160, 188.170, 116.020};
        List list = kama(array, 2);
        System.out.println(list);
        List list2 = MaUnit.sma(array, 2);
        System.out.println(list2);
    }

    public static List kama(double[] input, int period) {
        double[] output = new double[input.length];
        core.kama(0, input.length - 1, input, period, begin, length, output);
        return DataFormatTransformUtil.result2List(output);

    }

    public static List kama(List<MarketDomain> marketDomains, int period) {
        double[] input = DataFormatTransformUtil.marketDomainlist2Array(marketDomains);
        return kama(input, period);
    }


}
