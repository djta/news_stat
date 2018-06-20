package quant.tendencyStat;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MInteger;
import domain.MarketDomain;
import domain.talib.MacdDomain;
import quant.constant.TendencySign;
import talib.DataFormatTransformUtil;

import javax.crypto.Mac;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzyuyongmao on 2018/3/26.
 *
 * @Description MACD 指标主要是通过EMA、DIF和DEA（或叫MACD、DEM）这三值之间关系的研判，
 * DIF和DEA连接起来的移动平均线的研判以及DIF减去DEM值而绘制成的柱状图（BAR）的研判等来分析判断行情，
 * 预测XX中短期趋势的主要的XX技术分析指标。其中，DIF是核心，DEA是辅助。
 * DIF是快速平滑移动平均线（EMA1）和慢速平滑移动平均线（EMA2）的差。
 * BAR柱状图在股市技术软件上是用红柱和绿柱的收缩来研判行情。
 * <p>
 * MACD详解
 * https://www.zhihu.com/question/29595812
 * 《以交易为生》
 * <p>
 * 震荡市中，赚钱的核心策略在于“高抛低吸
 * <p>
 * 底背离
 * https://zhuanlan.zhihu.com/p/25967856
 */
public class MacdUnit extends TendencyUnit {
    private int shortPeriod;
    private int longPeriod;
    private int midPeriod;

    public MacdUnit(int shortPeriod, int longPeriod, int midPeriod) {
        this.shortPeriod = shortPeriod;
        this.longPeriod = longPeriod;
        this.midPeriod = midPeriod;
    }

    public static void main(String args[]) {
        double[] array = {207.650, 205.160, 210.870, 209.350, 207.250, 209.960, 207.650, 205.160, 188.170, 186.020};
        double[] outputMACD = new double[array.length];
        double[] outputSignl = new double[array.length];
        double[] outputHist = new double[array.length];
//        List<Double> result = dif(array, 2, 5);
//        System.out.println(result);
//        List<Double> dea = dea(array, 2, 5, 2);
//        System.out.println(dea);
//        for (int i = 0; i < dea.size(); i++) {
//            System.out.print(2 * (result.get(i) - dea.get(i)) + "\t");
//        }
//        System.out.println();
        core.macd(0, array.length - 1, array, 2, 5, 2, begin, length, outputMACD, outputSignl, outputHist);
        for (int i = 0; i < outputMACD.length; i++) {
            System.out.print(outputMACD[i] + "\t");
        }
        System.out.print("\n");
        for (int i = 0; i < outputMACD.length; i++) {
            System.out.print(outputSignl[i] + "\t");
        }
        System.out.print("\n");
        for (int i = 0; i < outputMACD.length; i++) {
            System.out.print(outputHist[i] + "\t");
        }
        System.out.println();
        List<MacdDomain> list = macd(array, 2, 5, 2);
        System.out.println(list);
    }

//    public static List dif(double[] input, int shortPeriod, int longPeriod) {
//        double[] shortOutput = new double[input.length];
//        core.ema(0, input.length - 1, input, shortPeriod, begin, length, shortOutput);
//        double[] longOutput = new double[input.length];
//        core.ema(0, input.length - 1, input, longPeriod, begin, length, longOutput);
//        List<Double> diffList = new ArrayList();
//        List<Double> shortList = DataFormatTransformUtil.result2List(shortOutput);
//        List<Double> longList = DataFormatTransformUtil.result2List(longOutput);
//        int size = shortList.size();
//        for (int i = 0; i < size; i++) {
//            if (shortList.get(i) == 0.0 || longList.get(i) == 0.0) {
//                diffList.add(0.0);
//            } else {
//                diffList.add(shortList.get(i) - longList.get(i));
//            }
//
//        }
//        return diffList;
//    }
//
//    public static List dea(double[] input, int shortPeriod, int longPeriod, int midPeriod) {
//        List difList = dif(input, shortPeriod, longPeriod);
////        System.out.println("difList:" + difList);
//        int index = difList.lastIndexOf(0.0);
//        double[] difArray = DataFormatTransformUtil.list2Array(difList.subList(index, difList.size()));
////        System.out.println("difArray:" + difArray.length);
////        System.out.println("difArray:" + index);
//        double[] deaArray = new double[difArray.length];
//        core.ema(0, difArray.length - 1, difArray, midPeriod, begin, length, deaArray);
//        List rawList = DataFormatTransformUtil.result2List(deaArray);
//        for (int i = 0; i < index; i++) {
//            rawList.add(i, 0.0);
//        }
//        return rawList;
//    }

    public static List<MacdDomain> macd(double[] input, int shortPeriod, int longPeriod, int midPeriod) {
        int arraylength = input.length;
        //DIF
        double[] outputMACD = new double[arraylength];
        //DEA
        double[] outputSignl = new double[arraylength];
        double[] outputHist = new double[arraylength];
        core.macd(0, arraylength - 1, input, shortPeriod, longPeriod, midPeriod, begin, length, outputMACD, outputSignl, outputHist);
        List<Double> difList = DataFormatTransformUtil.result2List(outputMACD);
        List<Double> deaList = DataFormatTransformUtil.result2List(outputSignl);
        List<Double> histList = DataFormatTransformUtil.result2List(outputHist);
        List<MacdDomain> resultDomainList = new ArrayList<MacdDomain>();
        for (int i = 0; i < difList.size(); i++) {
            MacdDomain md = new MacdDomain();
            md.setDea(deaList.get(i));
            md.setDif(difList.get(i));
            md.setHist(histList.get(i));
            resultDomainList.add(md);
//            System.out.println(md);
        }
        return resultDomainList;
    }

    public static List<MacdDomain> macd(List<MarketDomain> marketDomainList, int shortPeriod, int longPeriod, int midPeriod) {
        double[] input = DataFormatTransformUtil.marketDomainlist2Array(marketDomainList);
        return macd(input, shortPeriod, longPeriod, midPeriod);
    }


    /**
     * MACD 指标主要是通过EMA、DIF和DEA（或叫MACD、DEM）这三值之间关系的研判
     */
    public TendencySign getTendencySignTest1(List<MarketDomain> marketDomainList) {
        List<MacdDomain> macdDomainList = MacdUnit.macd(marketDomainList, shortPeriod, longPeriod, midPeriod);
        int size = macdDomainList.size();
        if (size < 2) {
            return TendencySign.WAIT;
        }
        MacdDomain macd1 = macdDomainList.get(size - 1);
        MacdDomain macd2 = macdDomainList.get(size - 2);
//        if (macd1.getDif() > macd2.getDif() && macd1.getDif() > macd1.getDea() && macd2.getDif() < macd2.getDea() && macd1.getDif() > 0) {
//            return TendencySign.BULL;
//        }
//        if (macd1.getDif() < macd2.getDif() && macd1.getDif() < macd1.getDea() && macd2.getDif() > macd2.getDea() && macd1.getDif() < 0) {
//            return TendencySign.BEAR;
//        }

//        if(macd1.getDea()>0&&macd1.getDif()>0&&macd1.getDif()>macd2.getDif()&&macd1.getDea()>macd2.getDea()){
//            return TendencySign.BULL;
//        }else if(macd1.getDea()<0&&macd1.getDif()<0&&macd1.getDif()<macd2.getDif()&&macd1.getDea()<macd2.getDea()){
//            return TendencySign.BEAR;
//        }

        if (macd1.getDea() > 0 && macd1.getDif() > 0 && macd2.getDif() < macd2.getDea() && macd1.getDif() > macd1.getDea()) {
            return TendencySign.BULL;
        }

        //0以上为强势，0以下为弱势
//        else if (macd1.getDea() < 0 && macd1.getDif() < 0 && macd2.getDif() < macd2.getDea() && macd1.getDif() > macd1.getDea()) {
//            return TendencySign.BULL;
//        }
        else if (macd1.getDea() < 0 && macd1.getDif() < 0 && macd2.getDif() > macd2.getDea() && macd1.getDif() < macd1.getDea()) {
            return TendencySign.BEAR;
        }
//        else if (macd1.getDea() < 0 && macd1.getDif() < 0 && macd2.getDif() > macd2.getDea() && macd1.getDif() < macd1.getDea()) {
//            return TendencySign.BEAR;
//        }


        return TendencySign.WAIT;
    }


    //反趋势
//    public  TendencySign getTendencySign(List<MarketDomain> marketDomainList) {
//        List<MacdDomain> macdDomainList = MacdUnit.macd(marketDomainList, shortPeriod, longPeriod, midPeriod);
//        int size = macdDomainList.size();
//        if (size < 2) {
//            return TendencySign.WAIT;
//        }
//        MacdDomain macd1 = macdDomainList.get(size - 1);
//        MacdDomain macd2 = macdDomainList.get(size - 2);
//        if (macd1.getDif() > macd2.getDif() && macd1.getDif() > macd1.getDea() && macd2.getDif() < macd2.getDea() && macd1.getDif() > 0) {
//            return TendencySign.BEAR;
//        }
//        if (macd1.getDif() < macd2.getDif() && macd1.getDif() < macd1.getDea() && macd2.getDif() > macd2.getDea() && macd1.getDif() < 0) {
//            return TendencySign.BULL;
//        }
//        return TendencySign.WAIT;
//    }

    //试试用5,15分钟线确认趋势。
    public TendencySign getTendencySignTest(List<MarketDomain> marketDomainList) {
        List<MacdDomain> macdDomainList = MacdUnit.macd(marketDomainList, shortPeriod, longPeriod, midPeriod);
        int size = macdDomainList.size();
        if (size < 2) {
            return TendencySign.WAIT;
        }
        MacdDomain macd1 = macdDomainList.get(size - 1);
        MacdDomain macd2 = macdDomainList.get(size - 2);
        //没啥用
        if (macd1.getDif() > 0 && macd2.getDif() < 0) {
            return TendencySign.BULL;
        }
        if (macd1.getDif() < 0 && macd2.getDif() > 0) {
            return TendencySign.BEAR;
        }


        return TendencySign.WAIT;
    }


    /*
  （1）顶背离
当股价K线图上的股票走势一峰比一峰高，股价一直在向上涨，而MACD指标图形上的由红柱构成的图形的走势是一峰比一峰低，
即当股价的高点比前一次的高点高、而MACD指标的高点比指标的前一次高点低，这叫顶背离现象。
顶背离现象一般是股价在高位即将反转转势的信号，表明股价短期内即将下跌，是卖出股票的信号。
（2）底背离
底背离一般出现在股价的低位区。当股价K线图上的股票走势，股价还在下跌，
而MACD指标图形上的由绿柱构成的图形的走势是一底比一底高，即当股价的低点比前一次低点底，
而指标的低点却比前一次的低点高，这叫底背离现象。底背离现象一般是预示股价在低位可能反转向上的信号，
表明股价短期内可能反弹向上，是短期买入股票的信号。

     */
    public TendencySign getTendencySign(List<MarketDomain> marketDomainList) {
        List<MacdDomain> macdDomainList = MacdUnit.macd(marketDomainList, shortPeriod, longPeriod, midPeriod);
        int size = macdDomainList.size();
        if (size < 2) {
            return TendencySign.WAIT;
        }
        MacdDomain macd1 = macdDomainList.get(size - 1);
        MacdDomain macd2 = macdDomainList.get(size - 2);
        int marketDomainSize = marketDomainList.size();
        MarketDomain marketDomain1 = marketDomainList.get(marketDomainSize - 1);
        MarketDomain marketDomain2 = marketDomainList.get(marketDomainSize - 2);
        //底背离
        if (macd1.getHist() > macd2.getHist() && marketDomain1.getClose() < marketDomain2.getClose()) {
            return TendencySign.BULL;
        }
        //顶背离
        if (macd1.getHist() < macd2.getHist() && marketDomain1.getClose() > marketDomain2.getClose()) {
            return TendencySign.BEAR;
        }


        return TendencySign.WAIT;
    }


    public TendencySign getTendencySignMultiPeriod(List<MarketDomain> marketDomainList) {
        List<MacdDomain> macdDomainList = MacdUnit.macd(marketDomainList, shortPeriod, longPeriod, midPeriod);
        int size = macdDomainList.size();
        if (size < 2) {
            return TendencySign.WAIT;
        }
        MacdDomain macd1 = macdDomainList.get(size - 1);
        MacdDomain macd2 = macdDomainList.get(size - 2);
        int marketDomainSize = marketDomainList.size();
        MarketDomain marketDomain1 = marketDomainList.get(marketDomainSize - 1);
        MarketDomain marketDomain2 = marketDomainList.get(marketDomainSize - 2);
        //底背离
        if (macd1.getHist() > macd2.getHist() && marketDomain1.getClose() < marketDomain2.getClose()) {
            return TendencySign.BULL;
        }
        //顶背离
        if (macd1.getHist() < macd2.getHist() && marketDomain1.getClose() > marketDomain2.getClose()) {
            return TendencySign.BEAR;
        }


        return TendencySign.WAIT;
    }

}
