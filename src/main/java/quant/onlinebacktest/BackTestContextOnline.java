package quant.onlinebacktest;

import domain.MarketDomain;
import jdbc.impl.MarketDaoImpl;
import quant.constant.TendencySign;
import quant.constant.TradeSign;
import quant.tendencyStat.BollingerBandUnit;
import quant.tendencyStat.RSIUnit;
import quant.tendencyStat.TendencyContext;
import quant.tendencyStat.TendencyUnit;
import quant.trade.TradeContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzyuyongmao on 2018/3/28.
 *
 * @Description
 */
public class BackTestContextOnline {
    public static void main(String args[]) {
        System.out.println("ok");
        MarketDaoImpl marketDao = new MarketDaoImpl();
        List<MarketDomain> marketDomains = marketDao.getKlineDataOnline("eosusdt");
//        System.out.println(marketDomains);
        System.out.println("size:" + marketDomains.size());
        List<TendencyUnit> tendencyUnits = new ArrayList<TendencyUnit>();

        tendencyUnits.add(new BollingerBandUnitOnline(50));//反趋势
//        tendencyUnits.add(new RSIUnit(25));//反趋势,RSI超买，超卖
        TendencyContext tc = new TendencyContext(0.2, 1, tendencyUnits);
        //
        TradeContext bc = new TradeContext(100000);
        for (int i = marketDomains.size(); i > 250; i--) {
            List<MarketDomain> list = marketDomains.subList(i - 250, i);
            List<MarketDomain> listReverse = listReverse(list);//时间降序排列（配合布林带计算）
            backTest(listReverse, tc, bc);
        }
        bc.resultStat();
        System.out.println("result:" + bc);

    }

    public static void backTest(List<MarketDomain> marketDomains, TendencyContext tendencyContext, TradeContext tradeContext) {
        TradeSign tradeSign = tendencyContext.getTradeSign(marketDomains);
//        double close = marketDomains.get(marketDomains.size() - 1).getClose();
        MarketDomain marketDomain = marketDomains.get(marketDomains.size() - 1);
        if (tradeSign.equals(TradeSign.OPEN)) {
            tradeContext.bull(marketDomain);
        }
        //跟踪止损
//        tradeContext.stopProfit(close);
        if (tradeSign.equals(TradeSign.CLOSE)) {
            tradeContext.bear(marketDomain);
        }
    }

    public static List<MarketDomain> listReverse(List<MarketDomain> marketDomains) {
        List<MarketDomain> marketDomainList = new ArrayList<MarketDomain>();
        for (int i = marketDomains.size() - 1; i >= 0; i--) {
            marketDomainList.add(marketDomains.get(i));
        }
        return marketDomainList;
    }

}
