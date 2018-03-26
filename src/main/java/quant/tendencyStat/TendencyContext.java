package quant.tendencyStat;

import domain.MarketDomain;
import domain.talib.DMADomain;
import domain.talib.MacdDomain;
import quant.constant.TendencySign;

import java.util.List;

/**
 * Created by 范志伟 on 2018-03-26.
 */
public class TendencyContext {
    public static void main(String args[]) {

    }

    public static TendencySign maSign(List<MarketDomain> marketDomainList, int shortPeriod, int longPeriod) {
        List<Double> sma = MaUnit.sma(marketDomainList, shortPeriod);
        List<Double> lma = MaUnit.sma(marketDomainList, longPeriod);
        int smaSize = sma.size();
        int lmaSize = lma.size();
        if (smaSize < 2 || lmaSize < 2) {
            return TendencySign.WAIT;
        }
        if (sma.get(smaSize - 1) > sma.get(smaSize - 2) && sma.get(smaSize - 1) > lma.get(lmaSize - 1) && sma.get(smaSize - 2) < lma.get(lmaSize - 2)) {
            return TendencySign.BULL;
        }
        if (lma.get(lmaSize - 1) < lma.get(lmaSize - 2) && sma.get(smaSize - 1) < lma.get(lmaSize - 1) && sma.get(smaSize - 2) > lma.get(lmaSize - 2)) {
            return TendencySign.BEAR;
        }
        return TendencySign.WAIT;
    }

    public static TendencySign macdSign(List<MarketDomain> marketDomainList, int shortPeriod, int longPeriod, int midPeriod) {
        List<MacdDomain> macdDomainList = MacdUnit.macd(marketDomainList, shortPeriod, longPeriod, midPeriod);
        int size = macdDomainList.size();
        if (size < 2) {
            return TendencySign.WAIT;
        }
        MacdDomain macd1 = macdDomainList.get(size - 1);
        MacdDomain macd2 = macdDomainList.get(size - 2);
        if (macd1.getDif() > macd2.getDif() && macd1.getDif() > macd1.getDea() && macd2.getDif() < macd2.getDea() && macd1.getDif() > 0) {
            return TendencySign.BULL;
        }
        if (macd1.getDif() < macd2.getDif() && macd1.getDif() < macd1.getDea() && macd2.getDif() > macd2.getDea() && macd1.getDif() < 0) {
            return TendencySign.BEAR;
        }
        return TendencySign.WAIT;
    }

    public static TendencySign dmaSign(List<MarketDomain> marketDomainList, int shortPeriod, int longPeriod, int midPeriod) {
        List<DMADomain> dmaDomainList = DMAUnit.dma(marketDomainList, shortPeriod, longPeriod, midPeriod);
        int size = dmaDomainList.size();
        if (size < 2) {
            return TendencySign.WAIT;
        }
        DMADomain dd1 = dmaDomainList.get(size - 1);
        DMADomain dd2 = dmaDomainList.get(size - 2);
        if (dd1.getDma() > dd2.getAma() && dd1.getDma() > dd1.getAma() && dd2.getDma() < dd2.getAma()) {
            return TendencySign.BULL;
        }
        if (dd1.getDma() < dd2.getAma() && dd1.getDma() < dd1.getAma() && dd2.getDma() > dd2.getAma()) {
            return TendencySign.BEAR;
        }
        return TendencySign.WAIT;
    }

    public static TendencySign trixSign(List<MarketDomain> marketDomainList, int period, int maPeriod) {
        List<Double> trix = TrixUnit.trix(marketDomainList, period);
        List<Double> mtrix = TrixUnit.mTrix(marketDomainList, period, maPeriod);
        int trixSize = trix.size();
        int mtrixSize = mtrix.size();
        if (trixSize < 2 || mtrixSize < 2) {
            return TendencySign.WAIT;
        }
        if (trix.get(trixSize - 1) > trix.get(trixSize - 2) && trix.get(trixSize - 1) > mtrix.get(mtrixSize - 1) && trix.get(trixSize - 2) < mtrix.get(mtrixSize - 2)) {
            return TendencySign.BULL;
        }
        if (trix.get(trixSize - 1) < trix.get(trixSize - 2) && trix.get(trixSize - 1) < mtrix.get(mtrixSize - 1) && trix.get(trixSize - 2) > mtrix.get(mtrixSize - 2)) {
            return TendencySign.BEAR;
        }
        return TendencySign.WAIT;

    }


}
