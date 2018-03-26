package quant.tendencyStat;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MInteger;
import talib.DataFormatTransformUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzyuyongmao on 2018/3/26.
 *
 * @Description
 */
public class MacdUnit {
    private static Core core = new Core();
    private int lookback = 0;
    private static MInteger begin = new MInteger();
    private static MInteger length = new MInteger();

    public static void main(String args[]) {
        double[] array = {207.650, 205.160, 210.870, 209.350, 207.250, 209.960, 207.650, 205.160, 188.170, 186.020};
        double[] outputMACD = new double[array.length];
        double[] outputSignl = new double[array.length];
        double[] outputHist = new double[array.length];
        List result = dif(array, 2, 5);
        System.out.println(result);
        List dea = dea(array, 2, 5, 2);
        System.out.println(dea);
        core.macd(0, array.length - 1, array, 2, 5, 3, begin, length, outputMACD, outputSignl, outputHist);
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


    }

    public static List dif(double[] input, int shortPeriod, int longPeriod) {
        double[] shortOutput = new double[input.length];
        core.ema(0, input.length - 1, input, shortPeriod, begin, length, shortOutput);
        double[] longOutput = new double[input.length];
        core.ema(0, input.length - 1, input, longPeriod, begin, length, longOutput);
        //
//        for (int i = 0; i < shortOutput.length; i++) {
//            System.out.print(shortOutput[i] + "\t");
//        }
//        System.out.println();
//        for (int i = 0; i < longOutput.length; i++) {
//            System.out.print(longOutput[i] + "\t");
//        }
//        System.out.println();
        //
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


}
