package qunat2.wrap;

import domain.MarketDomain;
import jdbc.impl.MarketDaoImpl;
import quant.tendencyStat.otherStat.KlineSlopeStat;
import qunat2.wrap.domain.PartDomain;

import java.util.ArrayList;
import java.util.List;

public class Part {
    public static void main(String args[]) {
        System.out.println("test");
        MarketDaoImpl marketDao = new MarketDaoImpl();
        List<MarketDomain> marketDomains = marketDao.getKlineDataOnline("eosusdt");
        List<MarketDomain> containsDomains = handleContain(marketDomains);
        System.out.println("marketDomains.size():" + marketDomains.size());
        System.out.println("containsDomains.size():" + containsDomains.size());
//        for(MarketDomain marketDomain:containsDomains){
//            marketDao.insertMarket(marketDomain,"wrapKline1min");
//        }

        List<PartDomain> partDomainList = getPart(containsDomains);
        System.out.println("partDomainList:" + partDomainList.size());
        for (PartDomain partDomain : partDomainList) {
            System.out.println(partDomain);
        }
    }

    //识别top,bottom
    public static List<PartDomain> getPart(List<MarketDomain> marketDomainList) {
        List<PartDomain> partDomains = new ArrayList<PartDomain>();
        for (int i = 2; i < marketDomainList.size(); i++) {
            MarketDomain leftDomain = marketDomainList.get(i - 2);
            MarketDomain middleDomain = marketDomainList.get(i - 1);
            MarketDomain rightDomain = marketDomainList.get(i);
            //top
            PartEnum partEnum = PartEnum.LEVEL;
            if (middleDomain.getHigh() >= leftDomain.getHigh()
                    && middleDomain.getHigh() >= rightDomain.getHigh()
                    && middleDomain.getLow() >= leftDomain.getLow()
                    && middleDomain.getLow() >= rightDomain.getLow()) {
//                System.out.println("top:" + middleDomain);
                partEnum = PartEnum.TOP;
            }
            //bottom
            if (middleDomain.getHigh() <= leftDomain.getHigh()
                    && middleDomain.getHigh() <= rightDomain.getHigh()
                    && middleDomain.getLow() <= leftDomain.getLow()
                    && middleDomain.getLow() <= rightDomain.getLow()) {
//                System.out.println("bottom:" + middleDomain);
                partEnum = PartEnum.BOTTOM;
            }
            if (partEnum != PartEnum.LEVEL) {
                PartDomain partDomain = new PartDomain();
                partDomain.setAmount(middleDomain.getAmount());
                partDomain.setClose(middleDomain.getClose());
                partDomain.setCount(middleDomain.getCount());
                partDomain.setHigh(middleDomain.getHigh());
                partDomain.setId(middleDomain.getId());
                partDomain.setLow(middleDomain.getLow());
                partDomain.setOpen(middleDomain.getOpen());
                partDomain.setSymbol(middleDomain.getSymbol());
                partDomain.setVol(middleDomain.getVol());
                partDomain.setPartEnum(partEnum);
                partDomains.add(partDomain);
            }
        }
        return partDomains;

    }

    //处理包含关系
    public static List<MarketDomain> handleContain(List<MarketDomain> marketDomainList) {
        List<MarketDomain> containsDomains = new ArrayList<MarketDomain>();

        MarketDomain changeDomain = new MarketDomain();
        for (int i = 10; i < marketDomainList.size(); i++) {
            double slopeValue = KlineSlopeStat.getKlineLinearRegSlope(marketDomainList.subList(i - 10, i));
//            System.out.println(slopeValue);
            MarketDomain leftDomain = changeDomain;
            MarketDomain middleDomain = marketDomainList.get(i);

            if (slopeValue > 0) {//up
                if (middleDomain.getHigh() >= leftDomain.getHigh()
                        && middleDomain.getLow() <= leftDomain.getLow()) {
                    middleDomain.setLow(leftDomain.getLow());
                    containsDomains.remove(containsDomains.size() - 1);
                    containsDomains.add(middleDomain);
                    changeDomain = middleDomain;
                } else if (leftDomain.getHigh() >= middleDomain.getHigh()
                        && leftDomain.getLow() <= middleDomain.getLow()) {
                    leftDomain.setLow(middleDomain.getLow());
                    containsDomains.remove(containsDomains.size() - 1);
                    containsDomains.add(leftDomain);
                    changeDomain = leftDomain;
                } else {
                    containsDomains.add(middleDomain);
                    changeDomain = middleDomain;
                }
            } else if (slopeValue < 0) {//down
                if (middleDomain.getHigh() >= leftDomain.getHigh()
                        && middleDomain.getLow() <= leftDomain.getLow()) {
                    middleDomain.setHigh(leftDomain.getHigh());
                    containsDomains.remove(containsDomains.size() - 1);
                    containsDomains.add(middleDomain);
                    changeDomain = middleDomain;
                } else if (leftDomain.getHigh() >= middleDomain.getHigh()
                        && leftDomain.getLow() <= middleDomain.getLow()) {
                    leftDomain.setHigh(middleDomain.getHigh());
                    containsDomains.remove(containsDomains.size() - 1);
                    containsDomains.add(leftDomain);
                    changeDomain = leftDomain;
                } else {
                    containsDomains.add(middleDomain);
                    changeDomain = middleDomain;
                }

            }

        }
        return containsDomains;
    }
}
