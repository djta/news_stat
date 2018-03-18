package util.stat;

import domain.MarketDomain;
import domain.stat.StandardStatDomain;

import java.util.List;

/**
 * Created by 范志伟 on 2018-03-17.
 */
public class StandardStatUtil {
    public static double getSumValue(List<MarketDomain> marketDomainList) {
        double sum = 0;
        for (MarketDomain md : marketDomainList) {
            sum += md.getClose();
        }
        return sum;
    }

    public static StandardStatDomain getSumValueReturnDomain(List<MarketDomain> marketDomainList) {
        StandardStatDomain ssd = new StandardStatDomain();
        double lowSum = 0;
        double highSum = 0;
        double closeSum = 0;
        double openSum = 0;
        for (MarketDomain md : marketDomainList) {
            lowSum += md.getLow();
            highSum += md.getHigh();
            closeSum += md.getClose();
            openSum += md.getOpen();
            ssd.setSymbol(md.getSymbol());
        }
        ssd.setCloseSum(closeSum);
        ssd.setOpenSum(openSum);
        ssd.setHighSum(highSum);
        ssd.setLowSum(lowSum);
        return ssd;
    }

    public static MarketDomain getMaxValue(List<MarketDomain> marketDomainList) {
        double closeMax = 0;
        double lowMax = 0;
        double highMax = 0;
        MarketDomain marketDomain = new MarketDomain();
        for (MarketDomain md : marketDomainList) {
            double close = md.getClose();
            double high = md.getHigh();
            double low = md.getLow();
            if (close >= closeMax) {
                closeMax = close;
            }
            if (high >= highMax) {
                highMax = high;
            }
            if (low >= lowMax) {
                lowMax = low;
            }
        }
        marketDomain.setHigh(highMax);
        marketDomain.setLow(lowMax);
        marketDomain.setClose(closeMax);
        return marketDomain;
    }

    public static MarketDomain getMinValue(List<MarketDomain> marketDomainList) {
        double closeMin = 0;
        double lowMin = 0;
        double highMin = 0;
        MarketDomain marketDomain = new MarketDomain();
        for (int i = 0; i < marketDomainList.size(); i++) {
            if (i == 0) {
                closeMin = marketDomainList.get(i).getClose();
                lowMin = marketDomainList.get(i).getLow();
                highMin = marketDomainList.get(i).getHigh();
                continue;
            }
            double close = marketDomainList.get(i).getClose();
            double high = marketDomainList.get(i).getHigh();
            double low = marketDomainList.get(i).getLow();
            if (close <= closeMin) {
                closeMin = close;
            }
            if (high <= highMin) {
                highMin = high;
            }
            if (low <= lowMin) {
                lowMin = low;
            }
        }
        marketDomain.setHigh(highMin);
        marketDomain.setLow(lowMin);
        marketDomain.setClose(closeMin);
        return marketDomain;
    }
}
