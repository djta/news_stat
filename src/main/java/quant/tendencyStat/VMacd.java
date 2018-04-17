package quant.tendencyStat;

import domain.MarketDomain;
import domain.talib.MacdDomain;
import quant.constant.TendencySign;
import talib.DataFormatTransformUtil;

import java.util.List;

/**
 * Created by hzyuyongmao on 2018/4/17.
 *
 * @Description
 */
public class VMacd extends TendencyUnit {

    private int shortPeriod;
    private int longPeriod;
    private int midPeriod;

    public VMacd(int shortPeriod, int longPeriod, int midPeriod) {
        this.shortPeriod = shortPeriod;
        this.longPeriod = longPeriod;
        this.midPeriod = midPeriod;
    }


    public  TendencySign getTendencySign(List<MarketDomain> marketDomainList) {
        List<MacdDomain> vMacdDomainList = macd(marketDomainList, shortPeriod, longPeriod, midPeriod);
        int size = vMacdDomainList.size();
        if (size < 2) {
            return TendencySign.WAIT;
        }
        MacdDomain macd1 = vMacdDomainList.get(size - 1);
        MacdDomain macd2 = vMacdDomainList.get(size - 2);
        if (macd1.getDif() > macd2.getDif() && macd1.getDif() > macd1.getDea() && macd2.getDif() < macd2.getDea() && macd1.getDif() > 0) {
            return TendencySign.BEAR;
        }
        if (macd1.getDif() < macd2.getDif() && macd1.getDif() < macd1.getDea() && macd2.getDif() > macd2.getDea() && macd1.getDif() < 0) {
            return TendencySign.BULL;
        }
        return TendencySign.WAIT;
    }


    public static  List<MacdDomain> macd(List<MarketDomain> marketDomainList, int shortPeriod, int longPeriod, int midPeriod) {
        double[] input = DataFormatTransformUtil.marketDomainlist2ArrayAmount(marketDomainList);
        return  MacdUnit.macd(input, shortPeriod, longPeriod, midPeriod);
    }



}
