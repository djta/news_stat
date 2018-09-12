package quant.tendencyStat.otherStat;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MInteger;
import domain.MarketDomain;
import domain.stat.otherStat.OtherStatDomain;
import talib.DataFormatTransformUtil;
import util.CacheUtil;

import java.util.List;

/**
 * Created by hzyuyongmao on 2018/6/22.
 *
 * @Description
 */
public class KlineSlopeStat {
    private static Core core = new Core();
    private static MInteger begin = new MInteger();
    private static MInteger length = new MInteger();


    public static void main(String args[]) {

        double[] array = {3,1};
        double result = getKlineLinearRegSlope(array);
        System.out.println(result);


    }

    public static List<Double> getKlineAmountLinearRegSlope(List<MarketDomain> marketDomains, int period) {

        double[] input = DataFormatTransformUtil.marketDomainlist2ArrayAmount(marketDomains);
        double[] output = new double[input.length];
        //斜率
        core.linearRegSlope(0, input.length - 1, input, period, begin, length, output);
        List<Double> slopeList = DataFormatTransformUtil.result2List(output);
        return slopeList;

    }

    public static double getKlineLinearRegSlope(List<MarketDomain> marketDomains) {
        double[] input = DataFormatTransformUtil.marketDomainlist2Array(marketDomains);
        double[] output = new double[input.length];
        //斜率
        core.linearRegSlope(0, input.length - 1, input, marketDomains.size(), begin, length, output);
        return output[0];
    }

    public static double getKlineLinearRegSlope(double[] array) {
        double[] output = new double[array.length];
        //斜率
        core.linearRegSlope(0, array.length - 1, array, array.length, begin, length, output);
//        List<Double> slopeList = DataFormatTransformUtil.result2List(output);
//        System.out.println(slopeList.get(slopeList.size() - 1));
//        int size = slopeList.size();
//        if (size > 1) {
//            return slopeList.get(slopeList.size() - 1);
//        } else {
//            return 0;
//        }
        return output[0];

    }

}
