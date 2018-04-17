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
 * @Description
 */
public class MacdUnit extends TendencyUnit{
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

    public static List dif(double[] input, int shortPeriod, int longPeriod) {
        double[] shortOutput = new double[input.length];
        core.ema(0, input.length - 1, input, shortPeriod, begin, length, shortOutput);
        double[] longOutput = new double[input.length];
        core.ema(0, input.length - 1, input, longPeriod, begin, length, longOutput);
        List<Double> diffList = new ArrayList();
        List<Double> shortList = DataFormatTransformUtil.result2List(shortOutput);
        List<Double> longList = DataFormatTransformUtil.result2List(longOutput);
        int size = shortList.size();
        for (int i = 0; i < size; i++) {
            if (shortList.get(i) == 0.0 || longList.get(i) == 0.0) {
                diffList.add(0.0);
            } else {
                diffList.add(shortList.get(i) - longList.get(i));
            }

        }
        return diffList;
    }

    public static List dea(double[] input, int shortPeriod, int longPeriod, int midPeriod) {
        List difList = dif(input, shortPeriod, longPeriod);
//        System.out.println("difList:" + difList);
        int index = difList.lastIndexOf(0.0);
        double[] difArray = DataFormatTransformUtil.list2Array(difList.subList(index, difList.size()));
//        System.out.println("difArray:" + difArray.length);
//        System.out.println("difArray:" + index);
        double[] deaArray = new double[difArray.length];
        core.ema(0, difArray.length - 1, difArray, midPeriod, begin, length, deaArray);
        List rawList = DataFormatTransformUtil.result2List(deaArray);
        for (int i = 0; i < index; i++) {
            rawList.add(i, 0.0);
        }
        return rawList;
    }

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
        List<MacdDomain> resultDomainList = new ArrayList<MacdDomain>();
        for (int i = 0; i < difList.size(); i++) {
            MacdDomain md = new MacdDomain();
            md.setDea(deaList.get(i));
            md.setDif(difList.get(i));
            resultDomainList.add(md);
        }
        return resultDomainList;
    }

    public static List<MacdDomain> macd(List<MarketDomain> marketDomainList, int shortPeriod, int longPeriod, int midPeriod) {
        double[] input = DataFormatTransformUtil.marketDomainlist2Array(marketDomainList);
        return macd(input, shortPeriod, longPeriod, midPeriod);
    }


//    public  TendencySign getTendencySign(List<MarketDomain> marketDomainList) {
//        List<MacdDomain> macdDomainList = MacdUnit.macd(marketDomainList, shortPeriod, longPeriod, midPeriod);
//        int size = macdDomainList.size();
//        if (size < 2) {
//            return TendencySign.WAIT;
//        }
//        MacdDomain macd1 = macdDomainList.get(size - 1);
//        MacdDomain macd2 = macdDomainList.get(size - 2);
//        if (macd1.getDif() > macd2.getDif() && macd1.getDif() > macd1.getDea() && macd2.getDif() < macd2.getDea() && macd1.getDif() > 0) {
//            return TendencySign.BULL;
//        }
//        if (macd1.getDif() < macd2.getDif() && macd1.getDif() < macd1.getDea() && macd2.getDif() > macd2.getDea() && macd1.getDif() < 0) {
//            return TendencySign.BEAR;
//        }
//        return TendencySign.WAIT;
//    }


     //反趋势
    public  TendencySign getTendencySign(List<MarketDomain> marketDomainList) {
        List<MacdDomain> macdDomainList = MacdUnit.macd(marketDomainList, shortPeriod, longPeriod, midPeriod);
        int size = macdDomainList.size();
        if (size < 2) {
            return TendencySign.WAIT;
        }
        MacdDomain macd1 = macdDomainList.get(size - 1);
        MacdDomain macd2 = macdDomainList.get(size - 2);
        if (macd1.getDif() > macd2.getDif() && macd1.getDif() > macd1.getDea() && macd2.getDif() < macd2.getDea() && macd1.getDif() > 0) {
            return TendencySign.BEAR;
        }
        if (macd1.getDif() < macd2.getDif() && macd1.getDif() < macd1.getDea() && macd2.getDif() > macd2.getDea() && macd1.getDif() < 0) {
            return TendencySign.BULL;
        }
        return TendencySign.WAIT;
    }

}
