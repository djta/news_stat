package quant.backtest;

import domain.MarketDomain;
import jdbc.impl.MarketDaoImpl;
import quant.PatternRecognition.Test;
import quant.constant.TendencySign;
import quant.constant.TradeSign;
import quant.tendencyStat.*;
import quant.trade.TradeContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzyuyongmao on 2018/3/28.
 *
 * @Description
 */
public class BackTestContext {
    public static void main(String args[]) {
        System.out.println("ok");
        MarketDaoImpl marketDao = new MarketDaoImpl();
        List<MarketDomain> marketDomains = marketDao.getKlineData("eosusdt");
        System.out.println(marketDomains.size());
//        for (MarketDomain md : marketDomains) {
//            System.out.println(md);
//        }
        //Units
        List<TendencyUnit> tendencyUnits = new ArrayList<TendencyUnit>();
        /*
           all:winsRate=0.375 winToLossRate=4.522
           trix:winsRate=0.32 winToLossRate=1.08135
           MA:winsRate=0.1724 winToLossRate=0.976
           MACD:winsRate=0.111 winToLossRate=0.111
           DMA:winsRate=0.625 winToLossRate=0.3806
           Bolling winsRate=0.9622 winToLossRate=22.327 //反趋势（最好与KDJ结合）
         */

//        tendencyUnits.add(new TrixUnit(12, 20));
//        tendencyUnits.add(new MaUnit(10, 20));
//        tendencyUnits.add(new MacdUnit(10, 20, 8));//反趋势（效果好）
//        tendencyUnits.add(new DMAUnit(10, 50, 10));//中长期
        tendencyUnits.add(new BollingerBandUnit(50));//反趋势（效果好）
//        tendencyUnits.add(new Test());
//        tendencyUnits.add(new RSIUnit(5));
//        tendencyUnits.add(new VMacd(10, 20, 8));
//        tendencyUnits.add(new VBollingBandUnit(100));
        TendencyContext tc = new TendencyContext(0.2, 0.5, tendencyUnits);
        //
        TradeContext bc = new TradeContext(100000);
        for (int i = 0; i < marketDomains.size() - 250; i++) {
            List<MarketDomain> list = marketDomains.subList(i, i + 250);
            backTest(list, tc, bc);
        }
//        bc.sell(bc.getBuyPrice());
//        System.out.println(marketDomains.get(marketDomains.size() - 1));
        bc.resultStat();
        System.out.println("result:" + bc.toSimpleString());


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

}
