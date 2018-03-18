package indicator.unit;

import domain.MarketDomain;
import domain.MarketMainDomain;
import indicator.IndicatorUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 范志伟 on 2018-03-17.
 * volume-weighted average price
 */
public class VwapUnit extends IndicatorUnit {

    public static double getVwapIndicator(List<MarketDomain> marketDomainList) {
        double volume = 0;//成交量
        double sum = 0;
        for (MarketDomain md : marketDomainList) {
            volume += md.getAmount();
            sum += md.getAmount() * md.getClose();
        }
        return sum / volume;

    }

    public static void main(String args[]) {
        MarketDomain md1 = new MarketDomain();
        md1.setAmount(25);
        md1.setClose(10);
        MarketDomain md2 = new MarketDomain();
        md2.setAmount(3);
        md2.setClose(20);
        List<MarketDomain> marketDomainList=new ArrayList<MarketDomain>();
        marketDomainList.add(md1);
        marketDomainList.add(md2);
      double result=  getVwapIndicator(marketDomainList);
      System.out.println(result);
    }
}
