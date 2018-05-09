package talib;

import domain.MarketDomain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hzyuyongmao on 2018/3/26.
 *
 * @Description
 */
public class DataFormatTransformUtil {
    public static Double[] double2DoubleArray(double[] input) {
        Double[] rawData = new Double[input.length];
        for (int i = 0; i < input.length; i++) {
            rawData[i] = input[i];
        }
        return rawData;
    }

    public static double[] Double2doubleArray(Double[] input) {
        double[] rawData = new double[input.length];
        for (int i = 0; i < input.length; i++) {
            rawData[i] = input[i];
        }
        return rawData;
    }

    public static List array2List(double[] input) {
        Double[] rawData = double2DoubleArray(input);
        ArrayList resultList = new ArrayList(Arrays.asList(rawData));
        return resultList;
    }

    public static double[] marketDomainlist2Array(List<MarketDomain> list) {
        double[] rawData = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            rawData[i] = list.get(i).getClose();
        }
        return rawData;
    }



    public static double[] marketDomainlist2ArrayAmount(List<MarketDomain> list) {
        double[] rawData = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            rawData[i] = list.get(i).getAmount();
        }
        return rawData;
    }


    public static List maResult2List(double[] input, int period) {
        List rawList = array2List(input);
        List outputList = rawList.subList(0, rawList.size() - period + 1);
        for (int i = 0; i < period - 1; i++) {
            outputList.add(0, 0.0);
        }
        return outputList;
    }

    public static double[] list2Array(List<Double> inputList) {
        int size = inputList.size();
        double[] rawArray = new double[size];
        for (int i = 0; i < size; i++) {
            rawArray[i] = inputList.get(i);
        }
        return rawArray;
    }


    public static List result2List(double[] input) {
        List rawList = array2List(input);
        int index = rawList.indexOf(0.0);
        List outputList = rawList.subList(0, index);
        int length = input.length;
        for (int i = 0; i < length - index; i++) {
            outputList.add(0, 0.0);
        }
        return outputList;
    }


}
