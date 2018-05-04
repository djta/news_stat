package quant.tendencyStat;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MInteger;
import domain.MarketDomain;
import quant.constant.TendencySign;
import talib.DataFormatTransformUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzyuyongmao on 2018/3/26.
 *
 * @Description
 */
public class MaUnit extends TendencyUnit {

    private int shortPeriod;
    private int longPeriod;

    public MaUnit(int shortPeriod, int longPeriod) {
        this.shortPeriod = shortPeriod;
        this.longPeriod = longPeriod;
    }

    public MaUnit() {

    }

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

    /*
       判断金叉和死叉
     */
    public TendencySign getTendencySignTest(List<MarketDomain> marketDomainList) {
        List<Double> sma = MaUnit.sma(marketDomainList, shortPeriod);
        List<Double> lma = MaUnit.sma(marketDomainList, longPeriod);
        int smaSize = sma.size();
        int lmaSize = lma.size();
        if (smaSize < 2 || lmaSize < 2) {
            return TendencySign.WAIT;
        }
        if (sma.get(smaSize - 1) > sma.get(smaSize - 2) && sma.get(smaSize - 1) > lma.get(lmaSize - 1) && sma.get(smaSize - 2) < lma.get(lmaSize - 2)) {
            return TendencySign.BULL;
        }
        if (lma.get(lmaSize - 1) < lma.get(lmaSize - 2) && sma.get(smaSize - 1) < lma.get(lmaSize - 1) && sma.get(smaSize - 2) > lma.get(lmaSize - 2)) {
            return TendencySign.BEAR;
        }
        return TendencySign.WAIT;
    }

    /*
        只判断长短均值线上下位置。
     */
    public TendencySign getTendencySign(List<MarketDomain> marketDomainList) {
        List<Double> sma = MaUnit.sma(marketDomainList, shortPeriod);
        List<Double> lma = MaUnit.sma(marketDomainList, longPeriod);
        int smaSize = sma.size();
        int lmaSize = lma.size();
        if (smaSize < 2 || lmaSize < 2) {
            return TendencySign.WAIT;
        }
        if (sma.get(smaSize - 1) > lma.get(lmaSize - 1)) {
            return TendencySign.BULL;
        }
        if (sma.get(smaSize - 1) < lma.get(lmaSize - 1)) {
            return TendencySign.BEAR;
        }
        return TendencySign.WAIT;


    }


    public static void main(String args[]) {
        double[] array = {207.650, 205.160, 210.870, 209.350, 207.250, 209.960, 207.650, 205.160, 188.170, 186.020};
        List list = sma(array, 2);
        System.out.println(list);
        System.out.println(list.get(0));
    }
}
