package quant.tendencyStat;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MInteger;
import domain.MarketDomain;
import quant.constant.TendencySign;
import quant.tendencyStat.otherStat.KlineSlopeStat;
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
    public TendencySign getTendencySignTest5(List<MarketDomain> marketDomainList) {
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
        if (sma.get(smaSize - 1) < lma.get(lmaSize - 1) && sma.get(smaSize - 2) > lma.get(lmaSize - 2)) {
            return TendencySign.BEAR;
        }
        return TendencySign.WAIT;


    }

    //成交量线
    //测试：MACD线、成交量线以及均线同时出现金叉的时候才是最好的入手时机？？
    //old
    public TendencySign getTendencySign(List<MarketDomain> marketDomainList) {
        double[] rawArray = DataFormatTransformUtil.marketDomainlist2ArrayAmount(marketDomainList);
        int inputLength = rawArray.length;
        double[] outputShortData = new double[inputLength];
        if (longPeriod > inputLength) {
            return TendencySign.WAIT;
        }
        core.sma(0, inputLength - 1, rawArray, shortPeriod, begin, length, outputShortData);
        List<Double> sma = DataFormatTransformUtil.result2List(outputShortData);
        //
        double[] outputLongData = new double[inputLength];
        core.sma(0, inputLength - 1, rawArray, longPeriod, begin, length, outputLongData);
        List<Double> lma = DataFormatTransformUtil.result2List(outputLongData);

        int smaSize = sma.size();
        int lmaSize = lma.size();
        if (smaSize < 2 || lmaSize < 2) {
            return TendencySign.WAIT;
        }
        if (sma.get(smaSize - 1) > sma.get(smaSize - 2) && sma.get(smaSize - 1) > lma.get(lmaSize - 1) && sma.get(smaSize - 2) < lma.get(lmaSize - 2)) {
            return TendencySign.BULL;
        }
        if (sma.get(smaSize - 1) < lma.get(lmaSize - 1) && sma.get(smaSize - 2) > lma.get(lmaSize - 2)) {
            return TendencySign.BEAR;
        }
        return TendencySign.WAIT;


    }

    //根据短线出击策略
    public TendencySign getTendencySignOld(List<MarketDomain> marketDomainList) {
        double[] rawArray = DataFormatTransformUtil.marketDomainlist2ArrayAmount(marketDomainList);
        int inputLength = rawArray.length;
        double[] outputShortData = new double[inputLength];
        double[] outputLongData = new double[inputLength];
        if (longPeriod > inputLength) {
            return TendencySign.WAIT;
        }
        //ma
        core.sma(0, inputLength - 1, rawArray, shortPeriod, begin, length, outputShortData);
        List<Double> sma = DataFormatTransformUtil.result2List(outputShortData);
        core.sma(0, inputLength - 1, rawArray, longPeriod, begin, length, outputLongData);
        List<Double> lma = DataFormatTransformUtil.result2List(outputLongData);

        int smaSize = sma.size();
        int lmaSize = lma.size();
        if (smaSize < 2 || lmaSize < 2) {
            return TendencySign.WAIT;
        }
        //lineage slope
        List<Double> shortSlopeList = KlineSlopeStat.getKlineAmountLinearRegSlope(marketDomainList, shortPeriod);
        List<Double> longSlopeList = KlineSlopeStat.getKlineAmountLinearRegSlope(marketDomainList, longPeriod);
        //
//        System.out.println("longSlope:" + longSlopeList.get(lmaSize - 1) +
//                "\tshortSlope:" + shortSlopeList.get(smaSize - 1));
        if (longSlopeList.get(lmaSize - 1) > 0.1
//                && longSlopeList.get(lmaSize - 2) > 0
                && (shortSlopeList.get(smaSize - 1) - longSlopeList.get(lmaSize - 1)) > 0.2
                && sma.get(smaSize - 1) > lma.get(lmaSize - 1) && sma.get(smaSize - 2) < lma.get(lmaSize - 2)) {
            return TendencySign.BULL;
        }
        if (
//                longSlopeList.get(lmaSize - 1) < 0 && longSlopeList.get(lmaSize - 2) < 0
//                && (shortSlopeList.get(smaSize - 1) - longSlopeList.get(lmaSize - 1)) < -0.02
//                &&
                sma.get(smaSize - 1) < lma.get(lmaSize - 1) && sma.get(smaSize - 2) > lma.get(lmaSize - 2)) {
            return TendencySign.BEAR;
        }
        return TendencySign.WAIT;

    }


    public TendencySign getTendencySignMultiPeriod(List<MarketDomain> marketDomainList) {
        double[] rawArray = DataFormatTransformUtil.marketDomainlist2ArrayAmount(marketDomainList);
        int inputLength = rawArray.length;
        double[] outputShortData = new double[inputLength];
        core.sma(0, inputLength - 1, rawArray, shortPeriod, begin, length, outputShortData);
        List<Double> sma = DataFormatTransformUtil.result2List(outputShortData);
        double[] outputLongData = new double[inputLength];
        core.sma(0, inputLength - 1, rawArray, longPeriod, begin, length, outputLongData);
        List<Double> lma = DataFormatTransformUtil.result2List(outputLongData);
        if (sma.get(sma.size() - 1) > lma.get(lma.size() - 1)) {
            return TendencySign.BULL;
        } else if (sma.get(sma.size() - 1) < lma.get(lma.size() - 1)) {
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
