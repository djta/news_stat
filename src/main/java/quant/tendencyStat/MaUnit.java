package quant.tendencyStat;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MInteger;
import domain.MarketDomain;
import talib.DataFormatTransformUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzyuyongmao on 2018/3/26.
 *
 * @Description
 */
public class MaUnit {

    private static Core core = new Core();
    private int lookback = 0;
    private static MInteger begin = new MInteger();
    private static MInteger length = new MInteger();

    public static List sma(double[] inputData, int period) {
        int inputLength = inputData.length;
        double[] outputData = new double[inputLength];
        if (period > inputLength) {
            return new ArrayList();
        }
        core.sma(0, inputLength - 1, inputData, period, begin, length, outputData);
        List list = DataFormatTransformUtil.maResult2List(outputData, period);
        return list;
    }

    public static List sma(List<MarketDomain> inputList, int period) {
        double[] rawArray = DataFormatTransformUtil.marketDomainlist2Array(inputList);
        int inputLength = rawArray.length;
        double[] outputData = new double[inputLength];
        if (period > inputLength) {
            return new ArrayList();
        }
        core.sma(0, inputLength - 1, rawArray, period, begin, length, outputData);
        List list = DataFormatTransformUtil.maResult2List(outputData, period);
        return list;
    }

    public static void main(String args[]) {

    }
}
