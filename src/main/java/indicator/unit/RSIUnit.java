package indicator.unit;

import domain.MarketDomain;
import indicator.IndicatorUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 范志伟 on 2018-03-19.
 */
public class RSIUnit extends IndicatorUnit {

    public static List<Double> getDiffValue(List<MarketDomain> marketDomainList) {
        double close = 0;
        List<Double> diffList = new ArrayList<Double>();
        for (int i = 0; i < marketDomainList.size(); i++) {
            if (i == 0) {
                close = marketDomainList.get(i).getClose();
                continue;
            }
            diffList.add(marketDomainList.get(i).getClose() - close);
            close = marketDomainList.get(i).getClose();
        }
        System.out.println(diffList.size());
        return diffList;
    }

    private static double getRsi(List<MarketDomain> marketDomainList) {
        int positiveDays = 0;
        int negativeDays = 0;
        double positiveSum = 0.0;
        double negativeSum = 0.0;
        List<Double> diffList = getDiffValue(marketDomainList);
        for (Double diff : diffList) {
            if (diff > 0) {
                positiveSum += diff;
                positiveDays++;
            } else if (diff < 0) {
                negativeSum += diff;
                negativeDays++;
            }
        }
        //相对强度
        double rs = (positiveSum / positiveDays) / (Math.abs(negativeSum) / negativeDays);
        System.out.println("rsi:" + positiveDays + "," + positiveSum + "," + negativeDays + "," + negativeSum);

        double rsi = (100 - 100 / (1 + rs));
        if (rsi == 0.0) {
            return 0.0;
        }
//        System.out.println(rsi);
        return rsi;
    }

    public static List<Double> getRSIValue(List<MarketDomain> marketDomainList, int recyle) {
        int listSize = marketDomainList.size();
        int end = listSize - recyle;
        List<Double> rsiList = new ArrayList<Double>();
        for (int i = 0; i < end + 1; i++) {
            rsiList.add(RSIUnit.getRsi(marketDomainList.subList(i, i + recyle)));
        }
        return rsiList;
    }


    public static void main(String args[]) {
        MarketDomain md1 = new MarketDomain();
        md1.setAmount(25);
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
        MarketDomain md6 = new MarketDomain();
        md6.setClose(9.10);
        MarketDomain md7 = new MarketDomain();
        md7.setClose(9.20);
        List<MarketDomain> marketDomainList = new ArrayList<MarketDomain>();
        marketDomainList.add(md1);
        marketDomainList.add(md2);
        marketDomainList.add(md3);
        marketDomainList.add(md4);
        marketDomainList.add(md5);
        marketDomainList.add(md6);
        marketDomainList.add(md7);
//        List<Double> result = getDiffValue(marketDomainList);
//        System.out.println(result);
        List<Double> rsiList = getRSIValue(marketDomainList, 3);
        System.out.println(rsiList);
    }
}
