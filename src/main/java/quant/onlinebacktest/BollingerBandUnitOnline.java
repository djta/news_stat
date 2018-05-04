package quant.onlinebacktest;

import com.tictactec.ta.lib.MAType;
import com.tictactec.ta.lib.MInteger;
import domain.MarketDomain;
import domain.talib.BollingerBandDomain;
import quant.constant.TendencySign;
import quant.tendencyStat.TendencyUnit;
import talib.DataFormatTransformUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BollingerBandUnitOnline extends TendencyUnit {
    private static MInteger begin = new MInteger();
    private static MInteger length = new MInteger();
    private static MAType maType = MAType.Kama;

    private int period;

    public BollingerBandUnitOnline(int period) {
        this.period = period;
    }

    public static void main(String args[]) {
        double[] array = {207.650, 205.160, 210.870, 209.350, 207.250, 209.960, 207.650, 205.160, 188.170, 186.020};
        List<BollingerBandDomain> list = bollingerBands(array, 2);
        System.out.println(list);
    }

    public static List<BollingerBandDomain> bollingerBands(double[] input, int period) {
        double[] upper = new double[input.length];
        double[] mid = new double[input.length];
        double[] lower = new double[input.length];
        //4,2比较好
        core.bbands(0, input.length - 1, input, period, 4, 2, maType, begin, length, upper, mid, lower);

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

    /*
       bear，反趋势
       破上下轨，则反转。
       中上轨和中下轨则反转。
       考虑结合kdj，胜率高，但行情很微小。
       https://wenku.baidu.com/view/636229a11eb91a37f1115ce8.html

       效果没有（//破上轨后移到上中轨，//破下轨后移到下中轨） 好

     */
    public TendencySign getTendencySignTest(List<MarketDomain> marketDomainList) {
        double[] input = DataFormatTransformUtil.marketDomainlist2Array(marketDomainList);
        List<BollingerBandDomain> bollingerBandDomains = bollingerBands(input, period);
        int bollingSize = bollingerBandDomains.size();
        int marketSize = marketDomainList.size();
        double close = marketDomainList.get(marketSize - 1).getClose();
        double upper = bollingerBandDomains.get(bollingSize - 1).getUpper();
        double lower = bollingerBandDomains.get(bollingSize - 1).getLower();
        if (upper - lower <= 0) {
            return TendencySign.WAIT;
        }
        if (close > upper) {
//            System.out.println("bear->" + "lower:" + lower + "\tupper:" + upper + "\t diff:" + (upper - lower) + "\t ts:" + marketDomainList.get(marketSize - 1).getId() + "\tclose:" + close);
            return TendencySign.BEAR;
        } else if (close <= lower) {
//            System.out.println("bull->" + "lower:" + lower + "\tupper:" + upper + "\t diff:" + (upper - lower) + "\t ts:" + marketDomainList.get(marketSize - 1).getId() + "\tclose:" + close);
            return TendencySign.BULL;
        }
        return TendencySign.WAIT;
    }

    //      //破上轨后移到上中轨，//破下轨后移到下中轨
    public TendencySign getTendencySign(List<MarketDomain> marketDomainList) {
        double[] input = DataFormatTransformUtil.marketDomainlist2Array(marketDomainList);
        List<BollingerBandDomain> bollingerBandDomains = bollingerBands(input, period);
        int bollingSize = bollingerBandDomains.size();
        int marketSize = marketDomainList.size();
        double close = marketDomainList.get(marketSize - 1).getClose();
        double upper = bollingerBandDomains.get(bollingSize - 1).getUpper();
        double lower = bollingerBandDomains.get(bollingSize - 1).getLower();
        if (upper - lower <= 0) {
            return TendencySign.WAIT;
        }
        if (marketDomainList.get(marketSize - 2).getClose() >= upper && close < upper) {
//            System.out.println("bear->" + "lower:" + lower + "\tupper:" + upper + "\t diff:" + (upper - lower) + "\t ts:" + marketDomainList.get(marketSize - 1).getId() + "\tclose:" + close);
            return TendencySign.BEAR;
        } else if (marketDomainList.get(marketSize - 2).getClose() <= lower && close > lower) {
//            System.out.println("bull->" + "lower:" + lower + "\tupper:" + upper + "\t diff:" + (upper - lower) + "\t ts:" + marketDomainList.get(marketSize - 1).getId() + "\tclose:" + close);
            return TendencySign.BULL;
        }
        return TendencySign.WAIT;
    }


    //破上轨回调，破下轨回调
//    public TendencySign getTendencySign(List<MarketDomain> marketDomainList) {
//        double[] input = DataFormatTransformUtil.marketDomainlist2Array(marketDomainList);
//        List<BollingerBandDomain> bollingerBandDomains = bollingerBands(input, period);
//        int bollingSize = bollingerBandDomains.size();
//        int marketSize = marketDomainList.size();
//        double close = marketDomainList.get(marketSize - 1).getClose();
//        double upper = bollingerBandDomains.get(bollingSize - 1).getUpper();
//        double lower = bollingerBandDomains.get(bollingSize - 1).getLower();
//        if (upper - lower <= 0) {
//            return TendencySign.WAIT;
//        }
//        if (marketDomainList.get(marketSize - 2).getClose() >= upper && close < marketDomainList.get(marketSize - 2).getClose()) {
////            System.out.println("bear->" + "lower:" + lower + "\tupper:" + upper + "\t diff:" + (upper - lower) + "\t ts:" + marketDomainList.get(marketSize - 1).getId() + "\tclose:" + close);
//            return TendencySign.BEAR;
//        } else if (marketDomainList.get(marketSize - 2).getClose() <= lower && close > marketDomainList.get(marketSize - 2).getClose()) {
////            System.out.println("bull->" + "lower:" + lower + "\tupper:" + upper + "\t diff:" + (upper - lower) + "\t ts:" + marketDomainList.get(marketSize - 1).getId() + "\tclose:" + close);
//            return TendencySign.BULL;
//        }
//        return TendencySign.WAIT;
//    }
    //http://www.360doc.com/content/16/0229/05/748316_538164884.shtml
    public TendencySign getTendencySign1(List<MarketDomain> marketDomainList) {
        double[] input = DataFormatTransformUtil.marketDomainlist2Array(marketDomainList);
        List<BollingerBandDomain> bollingerBandDomains = bollingerBands(input, period);
        int bollingSize = bollingerBandDomains.size();
        int marketSize = marketDomainList.size();
        double close = marketDomainList.get(marketSize - 1).getClose();
        double upper = bollingerBandDomains.get(bollingSize - 1).getUpper();
        double lower = bollingerBandDomains.get(bollingSize - 1).getLower();
        if (upper - lower <= 0) {
            return TendencySign.WAIT;
        }
        if (marketDomainList.get(marketSize - 2).getClose() >= upper && close < upper && bollingerBandDomains.get(bollingSize - 1).getUpper() > bollingerBandDomains.get(bollingSize - 2).getUpper()) {
//            System.out.println("bear->" + "lower:" + lower + "\tupper:" + upper + "\t diff:" + (upper - lower) + "\t ts:" + marketDomainList.get(marketSize - 1).getId() + "\tclose:" + close);
            return TendencySign.BEAR;
        } else if (marketDomainList.get(marketSize - 2).getClose() <= lower && close > lower && bollingerBandDomains.get(bollingSize - 2).getUpper() > bollingerBandDomains.get(bollingSize - 1).getUpper()) {
//            System.out.println("bull->" + "lower:" + lower + "\tupper:" + upper + "\t diff:" + (upper - lower) + "\t ts:" + marketDomainList.get(marketSize - 1).getId() + "\tclose:" + close);
            return TendencySign.BULL;
        }
        return TendencySign.WAIT;
    }


}
