package indicator.unit;

import domain.MarketDomain;
import domain.stat.StandardStatDomain;
import indicator.IndicatorUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 范志伟 on 2018-03-18.
 */
public class MacUntion extends IndicatorUnit {
    public static List<StandardStatDomain> getMovingAverageChannel(List<MarketDomain> marketDomainList, int recyle) {
        List<StandardStatDomain> list = MovingAverageUnit.getSimpleMovingAverageByDomain(marketDomainList, recyle);
        return list;
    }

    public static void main(String args[]) {
        MarketDomain md1 = new MarketDomain();
        md1.setAmount(25);
        md1.setClose(8.15);
        md1.setHigh(8.24);
        md1.setLow(8.01);
        MarketDomain md2 = new MarketDomain();
        md2.setAmount(3);
        md2.setClose(8.07);
        md2.setHigh(18.26);
        md2.setLow(14.05);
        md2.setOpen(8.6);
        MarketDomain md3 = new MarketDomain();
        md3.setAmount(4);
        md3.setClose(8.84);
        md3.setLow(8.14);
        md3.setHigh(15.4);
        md3.setOpen(8.6);
        MarketDomain md4 = new MarketDomain();
        md4.setAmount(4);
        md4.setClose(10.84);
        md4.setLow(13.14);
        md4.setHigh(21.4);
        md4.setOpen(8.6);
        MarketDomain md5 = new MarketDomain();
        md5.setAmount(4);
        md5.setClose(12.84);
        md5.setLow(81.14);
        md5.setHigh(19.4);
        md5.setOpen(28.6);
        md5.setSymbol("abtbtc");
        List<MarketDomain> marketDomainList = new ArrayList<MarketDomain>();
        marketDomainList.add(md1);
        marketDomainList.add(md2);
        marketDomainList.add(md3);
        marketDomainList.add(md4);
        marketDomainList.add(md5);
        List<StandardStatDomain> result = getMovingAverageChannel(marketDomainList, 5);
        System.out.println(result);
    }
}
