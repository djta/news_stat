package quant.tendencyStat;

import domain.MarketDomain;
import domain.talib.BollingerBandDomain;
import domain.talib.StochDomain;
import quant.constant.TendencySign;
import quant.onlinebacktest.BollingerBandUnitOnline;
import talib.DataFormatTransformUtil;
import util.DateUtil;

import java.util.List;

public class MixBollStochUnit extends TendencyUnit {
    private int bollPeriod;
    private int fastKPeriod;
    private int slowKPeriod;
    private int slowDPeriod;

    public MixBollStochUnit(int bollPeriod, int fastKPeriod, int slowKPeriod, int slowDPeriod) {
        this.bollPeriod = bollPeriod;
        this.fastKPeriod = fastKPeriod;
        this.slowKPeriod = slowKPeriod;
        this.slowDPeriod = slowDPeriod;

    }

    /*
        TradeContextStat{fund=390949.4278825668, cost=3760.85285716669, winsRate=0.8888888888888888,
        varFund=1.0367302311552746E9, varWinsRate=0.15339663988312627, winCount=8, failCount=1,
        varWinToLossRate=0.0, varEarningRate=0.15339663988312627, earingCount=0, lossCount=0,
        profit=0.0, profitPerOrder=0.0, varRovPerOrder=0.053744532595168315, varRovWinPerOrder=0.05226008541802216,
        varRovFailPerOrder=7.183949125286679E-4, meanFund=10566.200753582887, meanWinsRate=0.1891891891891892,
         meanWinToLossRate=0.0, meanEarningRate=0.1891891891891892, meanRovPerOrder=0.08127572467608023,
         meanWinPerOrder=0.08574287300996253, meanRovFailPerOrder=-0.004467148333882304}

         回测结果，胜率高，但成交率低

     */
    public TendencySign getTendencySignTest(List<MarketDomain> marketDomainList) {
        double[] input = DataFormatTransformUtil.marketDomainlist2Array(marketDomainList);
        List<BollingerBandDomain> bollingerBandDomains = BollingerBandUnitOnline.bollingerBands(input, bollPeriod);
        int bollingSize = bollingerBandDomains.size();
        int marketSize = marketDomainList.size();
        double close = marketDomainList.get(marketSize - 1).getClose();
        double upper = bollingerBandDomains.get(bollingSize - 1).getUpper();
        double lower = bollingerBandDomains.get(bollingSize - 1).getLower();
        if (upper - lower <= 0) {
            return TendencySign.WAIT;
        }
        //stoch
        List<StochDomain> stochDomains = StochUnit.getStochValue(fastKPeriod, slowKPeriod, slowDPeriod, marketDomainList);
        int stochSize = stochDomains.size();
        StochDomain stochDomain = stochDomains.get(stochSize - 1);
//        if (marketDomainList.get(marketSize - 2).getClose() >= upper && close < upper && stochDomain.getSlowK() < 25 && stochDomain.getSlowD() < 25) {
//            return TendencySign.BULL;
//        } else if (marketDomainList.get(marketSize - 2).getClose() >= upper && close < upper) {
//            return TendencySign.BEAR;
//        } else if (marketDomainList.get(marketSize - 2).getClose() <= lower && close > lower && stochDomain.getSlowK() > 75 && stochDomain.getSlowD() > 75) {
//            return TendencySign.BEAR;
//        } else if (marketDomainList.get(marketSize - 2).getClose() <= lower && close > lower) {
//            return TendencySign.BULL;
//        }

        if (marketDomainList.get(marketSize - 2).getClose() >= upper && close < upper && stochDomain.getSlowK() <= 25 && stochDomain.getSlowD() <= 25) {
            return TendencySign.BULL;
        } else if (marketDomainList.get(marketSize - 2).getClose() <= lower && close > lower && stochDomain.getSlowK() >= 75 && stochDomain.getSlowD() >= 75) {
            return TendencySign.BEAR;
        }

        return TendencySign.WAIT;
    }


    public TendencySign getTendencySign(List<MarketDomain> marketDomainList) {
        double[] input = DataFormatTransformUtil.marketDomainlist2Array(marketDomainList);
        List<BollingerBandDomain> bollingerBandDomains = BollingerBandUnitOnline.bollingerBands(input, bollPeriod);
        int bollingSize = bollingerBandDomains.size();
        int marketSize = marketDomainList.size();
        double close = marketDomainList.get(marketSize - 1).getClose();
        double upper = bollingerBandDomains.get(bollingSize - 1).getUpper();
        double lower = bollingerBandDomains.get(bollingSize - 1).getLower();
        if (upper - lower <= 0) {
            return TendencySign.WAIT;
        }
        //stoch
        List<StochDomain> stochDomains = StochUnit.getStochValue(fastKPeriod, slowKPeriod, slowDPeriod, marketDomainList);
        int stochSize = stochDomains.size();
        StochDomain stochDomain = stochDomains.get(stochSize - 1);
//        if (marketDomainList.get(marketSize - 2).getClose() >= upper && close < upper && stochDomain.getSlowK() < 25 && stochDomain.getSlowD() < 25) {
//            return TendencySign.BULL;
//        } else if (marketDomainList.get(marketSize - 2).getClose() >= upper && close < upper) {
//            return TendencySign.BEAR;
//        } else if (marketDomainList.get(marketSize - 2).getClose() <= lower && close > lower && stochDomain.getSlowK() > 75 && stochDomain.getSlowD() > 75) {
//            return TendencySign.BEAR;
//        } else if (marketDomainList.get(marketSize - 2).getClose() <= lower && close > lower) {
//            return TendencySign.BULL;
//        }

        if (marketDomainList.get(marketSize - 2).getClose() >= upper && close < upper && stochDomain.getSlowK() < 25 && stochDomain.getSlowD() < 25) {
            return TendencySign.BULL;
        } else if (marketDomainList.get(marketSize - 2).getClose() <= lower && close > lower && stochDomain.getSlowK() > 75 && stochDomain.getSlowD() > 75) {
            return TendencySign.BEAR;
        }

        return TendencySign.WAIT;
    }
}
