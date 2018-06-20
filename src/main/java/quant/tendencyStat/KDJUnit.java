package quant.tendencyStat;

import domain.MarketDomain;
import domain.talib.KDJDomain;
import quant.constant.TendencySign;
import util.stat.StandardStatUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzyuyongmao on 2018/6/19.
 *
 * @Description
 */
public class KDJUnit extends TendencyUnit {

    private int period;

    public KDJUnit(int period) {
        this.period = period;
    }

    public static List<KDJDomain> getKDJ(List<MarketDomain> marketDomainList, int period) {

        int listSize = marketDomainList.size();
        List<KDJDomain> kdjList = new ArrayList<KDJDomain>();
        List<Double> rsvList = new ArrayList<Double>();
        int end = listSize - period;
        for (int i = 0; i < end + 1; i++) {
            MarketDomain minDomain = StandardStatUtil.getMinValue(marketDomainList.subList(i, i + period));
            MarketDomain maxDomain = StandardStatUtil.getMaxValue(marketDomainList.subList(i, i + period));
            rsvList.add(((marketDomainList.get(i + period - 1).getClose() - minDomain.getLow()) / (maxDomain.getHigh() - minDomain.getLow())) * 100);
        }
        double initK = 0.0;
        //k value
        List<Double> kValueList = new ArrayList<Double>();
        for (int i = 0; i < rsvList.size(); i++) {
            if (i == 0) {
                initK = 50.0;
                kValueList.add(initK);
                continue;
            }
            initK = (2.0 / 3) * initK + (1.0 / 3) * rsvList.get(i);
            kValueList.add(initK);
        }
        //d value
        double initD = 0.0;
        List<Double> dValueList = new ArrayList<Double>();
        for (int i = 0; i < rsvList.size(); i++) {
            if (i == 0) {
                initD = 50.0;
                dValueList.add(initD);
                continue;
            }
            initD = (2.0 / 3) * initD + (1.0 / 3) * kValueList.get(i);
            dValueList.add(initD);
        }
        //j value
        List<Double> jValueList = new ArrayList<Double>();
        for (int i = 0; i < dValueList.size(); i++) {
            jValueList.add(3.0 * kValueList.get(i) - 2.0 * dValueList.get(i));
        }
        for (int i = 0; i < rsvList.size(); i++) {
            KDJDomain kdj = new KDJDomain();
            kdj.setK(kValueList.get(i));
            kdj.setD(dValueList.get(i));
            kdj.setJ(jValueList.get(i));
            kdjList.add(kdj);
        }
        return kdjList;

    }


    public TendencySign getTendencySign(List<MarketDomain> marketDomainList) {
        List<KDJDomain> kdjDomains = getKDJ(marketDomainList, period);
        

        return null;
    }

    public static void main(String args[]) {
        MarketDomain md2 = new MarketDomain();
        md2.setAmount(3);
        md2.setClose(8.07);
        md2.setHigh(18.26);
        md2.setLow(14.05);
        MarketDomain md1 = new MarketDomain();
        md1.setAmount(25);
        md1.setClose(8.15);
        md1.setHigh(8.24);
        md1.setLow(8.01);
        MarketDomain md3 = new MarketDomain();
        md3.setAmount(4);
        md3.setClose(8.84);
        md3.setLow(8.14);
        md3.setHigh(15.4);
        MarketDomain md4 = new MarketDomain();
        md4.setAmount(4);
        md4.setClose(10.84);
        md4.setLow(83.14);
        md4.setHigh(21.4);
        MarketDomain md5 = new MarketDomain();
        md5.setAmount(4);
        md5.setClose(12.84);
        md5.setLow(81.14);
        md5.setHigh(19.4);
        List<MarketDomain> marketDomainList = new ArrayList<MarketDomain>();
        marketDomainList.add(md1);
        marketDomainList.add(md2);
        marketDomainList.add(md3);
        marketDomainList.add(md4);
        marketDomainList.add(md5);
        List<KDJDomain> kdjDomainList = getKDJ(marketDomainList, 3);
        System.out.println(kdjDomainList);

    }

}
