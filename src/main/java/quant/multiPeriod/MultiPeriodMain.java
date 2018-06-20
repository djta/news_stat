package quant.multiPeriod;

import domain.MarketDomain;
import jdbc.impl.MarketDaoImpl;
import quant.constant.TradeSign;
import quant.onlinebacktest.BackTestAllSymbol;
import quant.onlinebacktest.BollingerBandUnitOnline;
import quant.onlinebacktest.TradeContextOnline;
import quant.tendencyStat.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzyuyongmao on 2018/6/13.
 *
 * @Description
 */
public class MultiPeriodMain {
    public static void main(String args[]) {
        MarketDaoImpl marketDao = new MarketDaoImpl();
        List<String> symobls = marketDao.getSymbols();
        //bigger
        List<TendencyUnit> biggerTendencyUnits = new ArrayList<TendencyUnit>();
        biggerTendencyUnits.add(new MaUnit(12, 18));
        biggerTendencyUnits.add(new RSIUnit(9, 3));
        TendencyContext biggerTc = new TendencyContext(1, 1, biggerTendencyUnits);
        //smaller
        List<TendencyUnit> smallerTendencyUnits = new ArrayList<TendencyUnit>();
        smallerTendencyUnits.add(new MaUnit(12, 18));
        smallerTendencyUnits.add(new RSIUnit(9, 3));
        TendencyContext smallerTc = new TendencyContext(1, 1, smallerTendencyUnits);

        List<TradeContextOnline> tradeContexts = new ArrayList<TradeContextOnline>();
        for (String symbol : symobls) {
//            if (!symbol.equals("btmusdt")) {
//                continue;
//            }
            List<MarketDomain> biggerMarketDomains = marketDao.getMultiPeriodKlineData(symbol, "getKline5MinData");
            List<MarketDomain> smallerMarketDomains = marketDao.getMultiPeriodKlineData(symbol, "getKline1MinData");
//            System.out.println("bigger size:" + biggerMarketDomains.size() + "\tsmall size:" + smallerMarketDomains.size());


            TradeContextOnline tradeContext = new TradeContextOnline(100000);
            for (int i = 250; i < biggerMarketDomains.size(); i++) {

                List<MarketDomain> biggerList = biggerMarketDomains.subList(i - 250, i);
                long biggerTs = biggerList.get(biggerList.size() - 1).getId();
                for (int j = 250; j < smallerMarketDomains.size(); j++) {
                    List<MarketDomain> smallerList = smallerMarketDomains.subList(j - 250, j);
                    long smallerTs = smallerList.get(smallerList.size() - 1).getId();
                    if (biggerTs < (smallerTs - 300) || biggerTs > (smallerTs + 300)) {
                        continue;
                    }
//                    System.out.println("biggerTs:" + biggerTs);
//                    System.out.println("smallerTs:" + smallerTs);
                    backTest(biggerList, smallerList, biggerTc, smallerTc, tradeContext);
                }
            }
            tradeContexts.add(tradeContext);
            tradeContext.resultStat();
            System.out.println(tradeContext);

        }
        System.out.println(BackTestAllSymbol.backTestAllSymbolsStat(tradeContexts));
    }

    public static void backTest(List<MarketDomain> biggerMarketDomains, List<MarketDomain> smallerMarketDomains, TendencyContext biggerTendencyContext,
                                TendencyContext smallerTendencyContext, TradeContextOnline tradeContext) {
        TradeSign biggerTradeSign = biggerTendencyContext.getTradeSignMultiPeriod(biggerMarketDomains);
        TradeSign smallerTradeSign = smallerTendencyContext.getTradeSign(smallerMarketDomains);

        MarketDomain marketDomain = smallerMarketDomains.get(smallerMarketDomains.size() - 1);
        if (biggerTradeSign.equals(TradeSign.OPEN) && smallerTradeSign.equals(TradeSign.OPEN)) {
            tradeContext.bull(marketDomain);
        }
        if (biggerTradeSign.equals(TradeSign.CLOSE) || smallerTradeSign.equals(TradeSign.CLOSE)) {
            tradeContext.bear(marketDomain);
        }
    }
}
