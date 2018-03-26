package quant.tendencyStat;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MAType;
import com.tictactec.ta.lib.MInteger;
import domain.stat.BollingBandDomain;
import domain.talib.BollingerBandDomain;
import talib.DataFormatTransformUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 范志伟 on 2018-03-27.
 */
public class BollingerBandUnit {
    private static Core core = new Core();
    private int lookback = 0;
    private static MInteger begin = new MInteger();
    private static MInteger length = new MInteger();
    private static MAType maType = MAType.Sma;

    public static void main(String args[]) {
        double[] array = {207.650, 205.160, 210.870, 209.350, 207.250, 209.960, 207.650, 205.160, 188.170, 186.020};
        List<BollingerBandDomain> list = bollingerBands(array, 2);
        System.out.println(list);
    }

    public static List<BollingerBandDomain> bollingerBands(double[] input, int period) {
        double[] upper = new double[input.length];
        double[] mid = new double[input.length];
        double[] lower = new double[input.length];
        core.bbands(0, input.length - 1, input, period, 2, 2, maType, begin, length, upper, mid, lower);
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
}
