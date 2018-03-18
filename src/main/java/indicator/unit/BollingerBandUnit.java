package indicator.unit;

import domain.MarketDomain;
import domain.stat.BollingBandDomain;
import indicator.IndicatorUnit;
import util.stat.MeanAndStdUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 范志伟 on 2018-03-18.
 */
public class BollingerBandUnit extends IndicatorUnit {
    public static  List<BollingBandDomain> getBollingerBand(List<MarketDomain> marketDomainList, int recyle) {
        List<Double> smaList = MovingAverageUnit.getSimpleMovingAverage(marketDomainList, recyle);
        double stdValue = MeanAndStdUtil.getStdValue(smaList);
        List<Double> upBolling = getListCalculator(smaList, 2 * stdValue);
        List<Double> downBolling = getListCalculator(smaList, -2 * stdValue);
        List<BollingBandDomain> bollingList=new ArrayList<BollingBandDomain>();
        for (int i = 0; i < smaList.size(); i++) {
            BollingBandDomain bbd = new BollingBandDomain();
            bbd.setMedium(smaList.get(i));
            bbd.setLower(downBolling.get(i));
            bbd.setUpwer(upBolling.get(i));
            bollingList.add(bbd);
        }
        return bollingList;
    }

    public static List<Double> getListCalculator(List<Double> list, double operatorValue) {
        List<Double> resultList = new ArrayList<Double>();
        for (Double val : list) {
            resultList.add(val + operatorValue);
        }
        return resultList;

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
        List<BollingBandDomain> resultList = getBollingerBand(marketDomainList, 5);
        System.out.println(resultList);
    }
}
