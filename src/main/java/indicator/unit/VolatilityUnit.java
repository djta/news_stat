package indicator.unit;

import domain.MarketDomain;
import indicator.IndicatorUnit;
import util.stat.MeanAndStdUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 范志伟 on 2018-03-17.
 * 波动率
 */
public class VolatilityUnit extends IndicatorUnit{
    /*
    单位为1的波动率
     */
    public static double getVolatility(List<MarketDomain> marketDomainList) {
        List<Double> logReturns = RateOfReturnUnit.getLogRateOfReturn(marketDomainList);
        double volatilityResult = MeanAndStdUtil.getStdValue(logReturns) / MeanAndStdUtil.getMeanValue(logReturns);
        return volatilityResult;
    }

    public static double getVolatility(List<MarketDomain> marketDomainList, int recyle) {
        double value = getVolatility(marketDomainList);
        return value / Math.sqrt(1.0/recyle);
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
        double volatilityValue1 = getVolatility(marketDomainList);
        System.out.println(volatilityValue1);
        double volatilityValue2 = getVolatility(marketDomainList,2);
        System.out.println(volatilityValue2);
    }
}
