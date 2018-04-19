package quant.onlinebacktest;

import domain.MarketDomain;
import jdbc.impl.MarketDaoImpl;
import quant.tendencyStat.MaUnit;
import quant.tendencyStat.MacdUnit;
import quant.tendencyStat.TendencyContext;
import quant.tendencyStat.TendencyUnit;
import quant.trade.TradeContext;
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
        tendencyUnits.add(new BollingerBandUnitOnline(50));//反趋势
//        tendencyUnits.add(new MacdUnit(10, 20, 8));//反趋势
//        tendencyUnits.add(new MaUnit(10, 20));
        TendencyContext tc = new TendencyContext(0.2, 1, tendencyUnits);
        MarketDaoImpl marketDao = new MarketDaoImpl();
        List<String> symobls = marketDao.getSymbols();
        List<TradeContextOnline> tradeContexts = new ArrayList<TradeContextOnline>();
        for (String symbol : symobls) {
            List<MarketDomain> marketDomains = marketDao.getKlineDataOnline(symbol);
            System.out.println("size:" + marketDomains.size());
            TradeContextOnline bc = new TradeContextOnline(100000);
            for (int i = marketDomains.size(); i > 250; i--) {
                List<MarketDomain> list = marketDomains.subList(i - 250, i);
                List<MarketDomain> listReverse = BackTestContextOnline.listReverse(list);//时间降序排列（配合布林带计算）
                BackTestContextOnline.backTest(listReverse, tc, bc);
            }
            bc.resultStat();
            System.out.println("symbol:" + symbol + "\tresult:" + bc.toSimpleString());
            tradeContexts.add(bc);
        }
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

        System.out.println(tradeContext);
    }
}
