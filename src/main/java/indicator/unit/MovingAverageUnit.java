package indicator.unit;

import domain.MarketDomain;
import domain.stat.StandardStatDomain;
import indicator.IndicatorUnit;
import util.stat.StandardStatUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 范志伟 on 2018-03-17.
 */
public class MovingAverageUnit extends IndicatorUnit {
    public static List<Double> getSimpleMovingAverage(List<MarketDomain> marketDomainList, int recyle) {
        int listSize = marketDomainList.size();
        int end = listSize - recyle;
        List<Double> movingAvgList = new ArrayList<Double>();
        for (int i = 0; i < end + 1; i++) {
            movingAvgList.add(StandardStatUtil.getSumValue(marketDomainList.subList(i, i + recyle)) / recyle);
        }
        return movingAvgList;

    }
    public static List<StandardStatDomain> getSimpleMovingAverageByDomain(List<MarketDomain> marketDomainList, int recyle) {
        int listSize = marketDomainList.size();
        int end = listSize - recyle;
        List<StandardStatDomain> movingAvgList = new ArrayList<StandardStatDomain>();
        for (int i = 0; i < end + 1; i++) {
            StandardStatDomain ssdResult=new StandardStatDomain();
            StandardStatDomain ssd=StandardStatUtil.getSumValueReturnDomain(marketDomainList.subList(i, i + recyle));
            ssdResult.setLowSum(ssd.getLowSum()/recyle);
            ssdResult.setHighSum(ssd.getHighSum()/recyle);
            ssdResult.setOpenSum(ssd.getOpenSum()/recyle);
            ssdResult.setCloseSum(ssd.getCloseSum()/recyle);
            ssdResult.setSymbol(ssd.getSymbol());
            movingAvgList.add(ssdResult);
        }
        return movingAvgList;

    }


    public static List<Double> getSimpleWeightedMovingAverage(List<MarketDomain> marketDomainList, int recyle) {
        int listSize = marketDomainList.size();
        int end = listSize - recyle;
        List<Double> movingAvgList = new ArrayList<Double>();
        for (int i = 0; i < end + 1; i++) {
            List<MarketDomain> subDomain = marketDomainList.subList(i, i + recyle);
            double weightedSum = 0;
            double amountSum = 0;
            for (MarketDomain md : subDomain) {
                weightedSum += md.getClose() * md.getAmount();
                amountSum += md.getAmount();
            }
            movingAvgList.add(weightedSum / amountSum);

        }
        return movingAvgList;

    }

    @Deprecated
    private static List<Double> getExpMovingAverage1(List<MarketDomain> marketDomainList, int recyle) {
        int listSize = marketDomainList.size();
        int end = listSize - recyle;
        List<Double> uniformDist = getUniformDist(5, -1, 0);
        double distSum = 0;
        List<Double> weightRawList = new ArrayList<Double>();
        for (double distValue : uniformDist) {
            double exp = Math.exp(distValue);
            distSum += exp;
            weightRawList.add(exp);
        }
        List<Double> weightList = new ArrayList<Double>();
        for (double distValue : weightRawList) {
            weightList.add(distValue / distSum);//权重归一化
        }
        System.out.println(uniformDist);
        System.out.println(weightList);

        List<Double> movingAvgList = new ArrayList<Double>();
        for (int i = 0; i < end + 1; i++) {
            movingAvgList.add(StandardStatUtil.getSumValue(marketDomainList.subList(i, i + recyle)) * weightList.get(i));
        }
        return movingAvgList;

    }

    private static double getExpMovingAverageOneValue(List<MarketDomain> marketDomainList, int recyle) {
        double k = 2 / (double) (recyle + 1);
        int size = marketDomainList.size();
        if (size == 0) {
            return 0;
        }
        if (size == 1) {
            return marketDomainList.get(0).getClose();
        }

        double result = marketDomainList.get(size - 1).getClose() * k + getExpMovingAverageOneValue(marketDomainList.subList(0, size - 1), recyle) * (1 - k);
        return result;
    }

    public static List<Double> getExpMovingAverage(List<MarketDomain> marketDomainList, int recyle) {
        int size = marketDomainList.size();
        List<Double> resultList = new ArrayList<Double>();
        if (size < recyle) {
            return resultList;
        }
        for (int i = 0; i < size - recyle + 1; i++) {
            resultList.add(getExpMovingAverageOneValue(marketDomainList.subList(i, i + recyle), recyle));
        }
        return resultList;
    }

    /*
       均匀分布
     */
    private static List<Double> getUniformDist(int recyle, int start, int end) {
        List<Double> list = new ArrayList<Double>();
        double sd = (double) (end - start) / (recyle - 1);
        for (int i = 0; i < recyle; i++) {
            double val = start + i * sd;
            list.add(val);
        }
        return list;
    }

    public static void main(String args[]) {
        MarketDomain md1 = new MarketDomain();
        md1.setAmount(5000);
        md1.setClose(8.15);
        MarketDomain md2 = new MarketDomain();
        md2.setAmount(3);
        md2.setClose(8.07);
        MarketDomain md3 = new MarketDomain();
        md3.setAmount(4);
        md3.setClose(8.84);
        MarketDomain md4 = new MarketDomain();
        md4.setAmount(4);
        md4.setClose(8.10);
        MarketDomain md5 = new MarketDomain();
        md5.setClose(8.40);
        md5.setAmount(4);
        MarketDomain md6 = new MarketDomain();
        md6.setClose(9.10);
        md6.setAmount(4);
        MarketDomain md7 = new MarketDomain();
        md7.setClose(9.20);
        md7.setAmount(4);
        List<MarketDomain> marketDomainList = new ArrayList<MarketDomain>();
        marketDomainList.add(md1);
        marketDomainList.add(md2);
        marketDomainList.add(md3);
        marketDomainList.add(md4);
        marketDomainList.add(md5);
        marketDomainList.add(md6);
        marketDomainList.add(md7);
        List<Double> list1 = getSimpleMovingAverage(marketDomainList, 5);
        System.out.println(list1);
        List<Double> expResult = getExpMovingAverage(marketDomainList, 2);
        System.out.println(expResult);
        List<Double> list3 = getSimpleWeightedMovingAverage(marketDomainList, 5);
        System.out.println(list3);
    }
}
