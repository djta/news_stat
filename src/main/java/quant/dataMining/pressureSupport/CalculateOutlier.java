package quant.dataMining.pressureSupport;

import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//找离群点
public class CalculateOutlier {
    public static void main(String args[]) {
//        NormalDistribution normalDistribution = new NormalDistribution(0, 1);
//        double S1 = normalDistribution.cumulativeProbability(3);
//        System.out.println(S1);
        List<Double> list = new ArrayList<Double>();
        list.add(1.2);
        list.add(0.5);
        list.add(1.6);
        list.add(1.0);
        list.add(0.6);
        list.add(0.2);
        list.add(0.1);
        list.add(0.6);
        box(list);

    }

    public static void box(List<Double> arr) {

        Collections.sort(arr);
        System.out.println(arr);
        double gap = (arr.size() - 1) / 4.0;
        System.out.println("gap:"+gap);
        //q1
        int q1Ceil = (int) Math.ceil(gap);
        int q1Floor = (int) Math.floor(gap);
//        System.out.println(gap + "," + q1Ceil + "," + q1Floor);
        double q1 = arr.get(q1Floor) + (arr.get(q1Ceil) - arr.get(q1Floor)) * 0.5;
//        System.out.println(arr.get(q1Floor));
//        System.out.println(arr.get(q1Ceil));
        System.out.println("q1:" + q1);
        //middle
        double middleGap = gap * 2;
        int middleCeil = (int) Math.ceil(middleGap);
        int middleFloor = (int) Math.floor(middleGap);
        double middle = arr.get(middleFloor) + (arr.get(middleCeil) - arr.get(middleFloor)) * 0.5;
        System.out.println("middle:" + middle);
        //q3
        double q3Gap=gap*3;
        int q3Ceil = (int) Math.ceil(q3Gap);
        int q3Floor = (int) Math.floor(q3Gap);
        double q3 = arr.get(q3Floor) + (arr.get(q3Ceil) - arr.get(q3Floor)) * 0.5;
        System.out.println("q3:" + q3);
    }
}
