package util.stat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 范志伟 on 2018-03-17.
 */
public class MeanAndStdUtil {
    public static double getMeanValue(List<Double> list) {
        double sum = 0;
        for (double value : list) {
            sum += value;
        }
        return sum / list.size();
    }

    public static double getVarValue(List<Double> list) {
        double mean = getMeanValue(list);
        double sum = 0;
        for (double value : list) {
            sum += Math.pow((value - mean), 2);
        }
        return sum / list.size();
    }

    public static double getStdValue(List<Double> list) {
        return Math.sqrt(getVarValue(list));
    }

    public static void main(String args[]) {
        List<Double> list = new ArrayList<Double>();
        list.add(1.0);
        list.add(2.0);
        list.add(5.0);
        double mean = getMeanValue(list);
        double val = getVarValue(list);
        double std = getStdValue(list);
        System.out.println("mean:" + mean + ",val:" + val + ",std:" + std);
    }
}
