package indicator.unit;

import domain.MarketDomain;
import indicator.IndicatorUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 范志伟 on 2018-03-17.
 * rate of return
 */
public class RateOfReturnUnit extends IndicatorUnit {
    public static List<Double> getSimpleRateOfReturn(List<MarketDomain> marketDomainList) {
        List<Double> diffList = new ArrayList<Double>();
        double init = 0;
        for (int i = 0; i < marketDomainList.size(); i++) {
            if (i == 0) {
                init = marketDomainList.get(i).getClose();
                continue;
            }
            double closeValue = marketDomainList.get(i).getClose();
            double diffValue = closeValue - init;
            double rate = diffValue / init;
            init = closeValue;
            diffList.add(rate);
        }
        return diffList;
    }
    public static List<Double> getLogRateOfReturn(List<MarketDomain> marketDomainList){
        List<Double> diffList = new ArrayList<Double>();
        double init=0;
        for (int i = 0; i < marketDomainList.size(); i++) {
            if(i==0){
                init = Math.log(marketDomainList.get(i).getClose());
                continue;
            }
            double closeValue=Math.log(marketDomainList.get(i).getClose());
            double diffValue=closeValue-init;
            init = closeValue;
            diffList.add(diffValue);
        }
        return diffList;

    }

    public static void main(String args[]) {
        MarketDomain md1 = new MarketDomain();
        md1.setAmount(25);
        md1.setClose(1);
        MarketDomain md2 = new MarketDomain();
        md2.setAmount(3);
        md2.setClose(2);
        MarketDomain md3 = new MarketDomain();
        md3.setAmount(4);
        md3.setClose(3);
        MarketDomain md4 = new MarketDomain();
        md4.setAmount(4);
        md4.setClose(4);
        List<MarketDomain> marketDomainList = new ArrayList<MarketDomain>();
        marketDomainList.add(md1);
        marketDomainList.add(md2);
        marketDomainList.add(md3);
        marketDomainList.add(md4);
        List<Double> resultlist1 = getSimpleRateOfReturn(marketDomainList);
        System.out.println(resultlist1);
        List<Double> resultlist2 = getLogRateOfReturn(marketDomainList);
        System.out.println(resultlist2);
    }
}
