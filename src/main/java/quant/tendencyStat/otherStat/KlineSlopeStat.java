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

    }

    public static List<Double> getKlineLinearRegSlope(List<MarketDomain> marketDomains, int period) {

        double[] input = DataFormatTransformUtil.marketDomainlist2ArrayAmount(marketDomains);
        double[] output = new double[input.length];
        //斜率
        core.linearRegSlope(0, input.length - 1, input, period, begin, length, output);
        List<Double> slopeList = DataFormatTransformUtil.result2List(output);
        return slopeList;

    }

}
