package qunat2.wrap.etl;

import domain.MarketDomain;
import jdbc.impl.MarketDaoImpl;
import qunat2.wrap.PartEnum;
import qunat2.wrap.domain.PartDomain;
import qunat2.wrap.domain.PenDomain;

import java.util.ArrayList;
import java.util.List;

public class Part {
    public static void main(String args[]) {
        System.out.println("test");
        MarketDaoImpl marketDao = new MarketDaoImpl();
        List<MarketDomain> marketDomains = marketDao.getKlineDataOnline("eosusdt");
        List<MarketDomain> containsDomains = contains(marketDomains);
        System.out.println("marketDomains.size():" + marketDomains.size());
        System.out.println("containsDomains.size():" + containsDomains.size());
//        for (MarketDomain marketDomain : containsDomains) {
//            marketDao.insertMarket(marketDomain, "wrapKline1min");
//        }
        List<PartDomain> partDomainList = getPart(containsDomains);
//        System.out.println("partDomainList.size():" + partDomainList.size());
//        for (PartDomain partDomain : partDomainList) {
//            System.out.println(partDomain);
//        }
        List<PenDomain> penDomainList = Pen.getPenInfo(partDomainList);
        System.out.println("penDomainList.size():" + penDomainList.size());
        for (PenDomain penDomain : penDomainList) {
//            System.out.println(penDomain);
            marketDao.insertPenMarket(penDomain,"penKLine");
        }

    }

    //http://www.zcaijing.com/chanlunjishutujie/16441.html
    public static List<MarketDomain> contains(List<MarketDomain> marketDomainList) {

        List<MarketDomain> containsDomainList = new ArrayList<MarketDomain>();
        containsDomainList.add(marketDomainList.get(0));
        containsDomainList.add(marketDomainList.get(1));
        for (int i = 2; i < marketDomainList.size(); i++) {
            MarketDomain kline1 = containsDomainList.get(containsDomainList.size() - 2);
            MarketDomain kline2 = containsDomainList.get(containsDomainList.size() - 1);
            MarketDomain kline3 = marketDomainList.get(i);
            //
            MarketDomain containsKline = new MarketDomain();
            containsKline.setSymbol(kline3.getSymbol());
            if (kline2.getHigh() >= kline3.getHigh() && kline2.getLow() <= kline3.getLow()) {//kline2 contains kline3
                if (kline2.getHigh() >= kline1.getHigh()) {//up
                    containsKline.setHigh(kline2.getHigh());
                    containsKline.setLow(kline3.getLow());
                    containsKline.setId(kline2.getId());
                } else if (kline2.getHigh() < kline1.getHigh()) {//down
                    containsKline.setHigh(kline3.getHigh());
                    containsKline.setLow(kline2.getLow());
                    containsKline.setId(kline2.getId());
                }
                containsDomainList.remove(containsDomainList.size() - 1);

            } else if (kline2.getHigh() < kline3.getHigh() && kline2.getLow() > kline3.getLow()) {
                //kline3 contains kline2
                if (kline2.getHigh() >= kline1.getHigh()) {//up
                    containsKline.setId(kline3.getId());
                    containsKline.setHigh(kline3.getHigh());
                    containsKline.setLow(kline2.getLow());
                } else if (kline2.getHigh() < kline1.getHigh()) {//down
                    containsKline.setId(kline3.getId());
                    containsKline.setHigh(kline2.getHigh());
                    containsKline.setLow(kline3.getLow());
                }
                containsDomainList.remove(containsDomainList.size() - 1);
            } else {
                containsKline.setHigh(kline3.getHigh());
                containsKline.setLow(kline3.getLow());
                containsKline.setId(kline3.getId());
            }
            containsDomainList.add(containsKline);

        }
        return containsDomainList;

    }

    //http://www.zcaijing.com/chanlunjishutujie/16444.html
    public static List<PartDomain> getPart(List<MarketDomain> marketDomainList) {
        List<PartDomain> partDomainList = new ArrayList<PartDomain>();
        int partStep = 0;//分型步长
        for (int i = 2; i < marketDomainList.size(); i++) {
            if (partStep != 0) {
                partStep--;
                continue;
            }
            MarketDomain kline1 = marketDomainList.get(i - 2);
            MarketDomain kline2 = marketDomainList.get(i - 1);
            MarketDomain kline3 = marketDomainList.get(i);
            PartDomain partDomain = new PartDomain();

            partDomain.setSymbol(kline2.getSymbol());
            if (kline2.getHigh() >= kline1.getHigh() && kline2.getLow() >= kline1.getLow()
                    && kline2.getHigh() >= kline3.getHigh() && kline2.getLow() >= kline3.getLow()) {
                partDomain.setHigh(kline2.getHigh());
                partDomain.setLow(kline2.getLow());
                partDomain.setId(kline2.getId());
                partDomain.setPartEnum(PartEnum.TOP);
                partStep = 2;
            } else if (kline2.getHigh() < kline1.getHigh() && kline2.getLow() < kline1.getLow()
                    && kline2.getHigh() < kline3.getHigh() && kline2.getLow() < kline3.getLow()) {
                partDomain.setHigh(kline2.getHigh());
                partDomain.setLow(kline2.getLow());
                partDomain.setId(kline2.getId());
                partDomain.setPartEnum(PartEnum.BOTTOM);
                partStep = 2;
            } else {
                partDomain.setHigh(kline1.getHigh());
                partDomain.setLow(kline1.getLow());
                partDomain.setId(kline1.getId());
                partDomain.setPartEnum(PartEnum.LEVEL);
            }
            partDomainList.add(partDomain);
        }
        return partDomainList;

    }


}
