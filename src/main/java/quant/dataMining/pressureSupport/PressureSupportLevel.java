package quant.dataMining.pressureSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
   list长度大于2才可用
 */
public class PressureSupportLevel {
    public static void main(String args[]) {
        List<Double> doubleList = new ArrayList<Double>();
        doubleList.add(1.0);
        doubleList.add(0.0);
        doubleList.add(0.5);
        getLeastLine(doubleList);
    }

    public static void getLeastLine(List<Double> doubleList) {
        double max = Collections.max(doubleList);
        double min = Collections.min(doubleList);
        System.out.println(max + "," + min);
        double diff = max - min;
        double result = Double.MAX_VALUE;
        double middleValue = 0;
        for (int i = 0; i < 100; i++) {
            double rand = Math.random();
            double middle = max - diff * rand;
            System.out.println("middle:" + middle);
            double initValue = 0;
            for (double value : doubleList) {
                initValue += Math.abs(middle - value);
            }
            if (result > initValue) {
                result = initValue;
                middleValue = middle;
            }
        }
        System.out.println("middleValue:" + middleValue);

    }
}
