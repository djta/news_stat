package quant.tendencyStat.otherStat;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MInteger;
import domain.MarketDomain;
import domain.stat.otherStat.OtherStatDomain;
import talib.DataFormatTransformUtil;
import util.CacheUtil;

import java.util.List;

/**
 * Created by hzyuyongmao on 2018/6/20.
 *
 * @Description 目前只考虑回归斜率
 */
public class AmountStat {
    private static Core core = new Core();
    private static MInteger begin = new MInteger();
    private static MInteger length = new MInteger();


    public static void main(String args[]) {

    }

    public static void getAmountLinearRegSlope(List<MarketDomain> marketDomains, String symbol, int period) {
        OtherStatDomain otherStatDomain = CacheUtil.otherStateCache.getIfPresent(symbol);
        if (otherStatDomain == null) {
            otherStatDomain = new OtherStatDomain();
            otherStatDomain.setSymbol(symbol);
            CacheUtil.otherStateCache.put(symbol, otherStatDomain);
        }
        double[] input = DataFormatTransformUtil.marketDomainlist2ArrayAmount(marketDomains);
        double[] output = new double[input.length];
        //斜率
        core.linearRegSlope(0, input.length - 1, input, period, begin, length, output);
        List<Double> slopeList = DataFormatTransformUtil.result2List(output);
        otherStatDomain.setAmountLinearRegSlopeList(slopeList);

    }
}
