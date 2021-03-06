package quant.onlinebacktest;

import domain.MarketDomain;
import jdbc.impl.MarketDaoImpl;
import quant.tendencyStat.*;
import quant.tendencyStat.newTendency.BollNewUnit;
import quant.tendencyStat.newTendency.MaNewUnit;
import quant.tendencyStat.newTendency.RSINewUnit;
import util.stat.MeanAndStdUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzyuyongmao on 2018/4/19.
 *
 * @Description
 */
public class BackTestAllSymbol {
    public static void main(String args[]) {
        List<TendencyUnit> tendencyUnits = new ArrayList<TendencyUnit>();
//        tendencyUnits.add(new BollingerBandUnitOnline(50));//反趋势
        tendencyUnits.add(new BollNewUnit(50));//
//        tendencyUnits.add(new MacdUnit(12, 26, 9));//反趋势
//        tendencyUnits.add(new MaUnit(5, 10));
//        tendencyUnits.add(new MaNewUnit(5, 10, 30));
//        tendencyUnits.add(new RSINewUnit(6, 12));
//        tendencyUnits.add(new MixTendencyUnit(10, 9, 20));
//        tendencyUnits.add(new StochUreturn TendencySign.BULL;nit(9, 3, 3));
//        tendencyUnits.add(new MixBollStochUnit(20, 9, 3, 3));
//        tendencyUnits.add(new RSIUnit(9, 3));
        TendencyContext tc = new TendencyContext(1, 1, tendencyUnits);
        MarketDaoImpl marketDao = new MarketDaoImpl();
        List<String> symobls = marketDao.getSymbols();
        List<TradeContextOnline> tradeContexts = new ArrayList<TradeContextOnline>();

        for (String symbol : symobls) {
//            if (!symbol.equals("zecusdt") && !symbol.equals("btcusdt")) {
//                continue;
//            }
            List<MarketDomain> marketDomains = marketDao.getKlineDataOnline(symbol);
            System.out.println("size:" + marketDomains.size());
            TradeContextOnline bc = new TradeContextOnline(100000);
            for (int i = marketDomains.size(); i > 250; i--) {
                List<MarketDomain> list = marketDomains.subList(i - 250, i);
                List<MarketDomain> listReverse = BackTestContextOnline.listReverse(list);//时间降序排列（配合布林带计算）
                BackTestContextOnline.backTest(listReverse, tc, bc);
            }
            bc.resultStat();
            System.out.println("symbol:" + symbol + "\tresult:" + bc);
//            System.out.println(JSON.toJSONString(bc));

            if (bc.getFund() != 100000) {
                tradeContexts.add(bc);
            }
        }

        TradeContextStat tradeContext = backTestAllSymbolsStat(tradeContexts);
        System.out.println(tradeContext);
    }


    public static TradeContextStat backTestAllSymbolsStat(List<TradeContextOnline> tradeContexts) {
        TradeContextStat tradeContext = new TradeContextStat();
        List<Double> varRovPerOrder = new ArrayList<Double>();
        List<Double> varRovWinPerOrder = new ArrayList<Double>();
        List<Double> varRovFailPerOrder = new ArrayList<Double>();
        List<Double> varWinToLossRate = new ArrayList<Double>();
        List<Double> varEarningRate = new ArrayList<Double>();
        List<Double> varWinsRate = new ArrayList<Double>();
        List<Double> varFund = new ArrayList<Double>();
        for (TradeContextOnline tradeContextOnline : tradeContexts) {
            double fund = tradeContextOnline.getFund() - 100000;
            tradeContext.setFund(fund + tradeContext.getFund());
            tradeContext.setCost(tradeContext.getCost() + tradeContextOnline.getCost());
            tradeContext.setWinCount(tradeContextOnline.getWinCount() + tradeContext.getWinCount());
            tradeContext.setFailCount(tradeContextOnline.getFailCount() + tradeContext.getFailCount());
            varRovPerOrder.add(tradeContextOnline.getRovPerOrder());
            varRovWinPerOrder.add(tradeContextOnline.getRovWinPerOrder());
            varRovFailPerOrder.add(tradeContextOnline.getRovFailPerOrder());
            varWinToLossRate.add(tradeContextOnline.getWinToLossRate());
            varEarningRate.add(tradeContextOnline.getEarningRate());
            varWinsRate.add(tradeContextOnline.getWinsRate());
            varFund.add(fund);
        }
        tradeContext.setWinsRate((double) tradeContext.getWinCount() / (tradeContext.getFailCount() + tradeContext.getWinCount()));
        tradeContext.setVarEarningRate(MeanAndStdUtil.getVarValue(varEarningRate));
        tradeContext.setVarFund(MeanAndStdUtil.getVarValue(varFund));
        tradeContext.setVarRovFailPerOrder(MeanAndStdUtil.getVarValue(varRovFailPerOrder));
        tradeContext.setVarRovPerOrder(MeanAndStdUtil.getVarValue(varRovPerOrder));
        tradeContext.setVarRovWinPerOrder(MeanAndStdUtil.getVarValue(varRovWinPerOrder));
        tradeContext.setVarWinToLossRate(MeanAndStdUtil.getVarValue(varWinToLossRate));
        tradeContext.setVarWinsRate(MeanAndStdUtil.getVarValue(varWinsRate));
        //
        tradeContext.setMeanEarningRate(MeanAndStdUtil.getMeanValue(varEarningRate));
        tradeContext.setMeanFund(MeanAndStdUtil.getMeanValue(varFund));
        tradeContext.setMeanRovFailPerOrder(MeanAndStdUtil.getMeanValue(varRovFailPerOrder));
        tradeContext.setMeanRovPerOrder(MeanAndStdUtil.getMeanValue(varRovPerOrder));
        tradeContext.setMeanWinPerOrder(MeanAndStdUtil.getMeanValue(varRovWinPerOrder));
        tradeContext.setMeanWinToLossRate(MeanAndStdUtil.getMeanValue(varWinToLossRate));
        tradeContext.setMeanWinsRate(MeanAndStdUtil.getMeanValue(varWinsRate));
        return tradeContext;
    }


}
