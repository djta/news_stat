package quant.onlinebacktest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quant.trade.TradeDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzyuyongmao on 2018/4/19.
 *
 * @Description
 */
public class TradeContextStat {
    private static final Logger logger = LoggerFactory.getLogger(TradeContextStat.class);
    private double fund;//盈利资金
    private double cost = 0;//总手续费
    private double winsRate;//总胜率
    //var方差
    private double varFund;//盈利资金
    private double varWinsRate;//获胜率
    private int winCount;
    private int failCount;
    private double varWinToLossRate;//赢亏比
    private double varEarningRate;//获利率
    private int earingCount;//获利数
    private int lossCount;//亏损数
    private double profit;//利润数
    private double profitPerOrder;//每单利润
    private double varRovPerOrder;//每单资产收益率
    private double varRovWinPerOrder;//盈利每单资产收益率
    private double varRovFailPerOrder;//亏损每单资产收益率
    //mean
    private double meanFund;//盈利资金
    private double meanWinsRate;//获胜率
    private double meanWinToLossRate;//赢亏比
    private double meanEarningRate;//获利率
    private double meanRovPerOrder;//每单资产收益率
    private double meanWinPerOrder;//盈利每单资产收益率
    private double meanRovFailPerOrder;//亏损每单资产收益率


    public double getFund() {
        return fund;
    }

    public void setFund(double fund) {
        this.fund = fund;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getVarFund() {
        return varFund;
    }

    public void setVarFund(double varFund) {
        this.varFund = varFund;
    }

    public double getVarWinsRate() {
        return varWinsRate;
    }

    public void setVarWinsRate(double varWinsRate) {
        this.varWinsRate = varWinsRate;
    }

    public int getWinCount() {
        return winCount;
    }

    public void setWinCount(int winCount) {
        this.winCount = winCount;
    }

    public int getFailCount() {
        return failCount;
    }

    public void setFailCount(int failCount) {
        this.failCount = failCount;
    }

    public double getVarWinToLossRate() {
        return varWinToLossRate;
    }

    public void setVarWinToLossRate(double varWinToLossRate) {
        this.varWinToLossRate = varWinToLossRate;
    }

    public double getVarEarningRate() {
        return varEarningRate;
    }

    public void setVarEarningRate(double varEarningRate) {
        this.varEarningRate = varEarningRate;
    }

    public int getEaringCount() {
        return earingCount;
    }

    public void setEaringCount(int earingCount) {
        this.earingCount = earingCount;
    }

    public int getLossCount() {
        return lossCount;
    }

    public void setLossCount(int lossCount) {
        this.lossCount = lossCount;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getProfitPerOrder() {
        return profitPerOrder;
    }

    public void setProfitPerOrder(double profitPerOrder) {
        this.profitPerOrder = profitPerOrder;
    }

    public double getVarRovPerOrder() {
        return varRovPerOrder;
    }

    public void setVarRovPerOrder(double varRovPerOrder) {
        this.varRovPerOrder = varRovPerOrder;
    }

    public double getVarRovWinPerOrder() {
        return varRovWinPerOrder;
    }

    public void setVarRovWinPerOrder(double varRovWinPerOrder) {
        this.varRovWinPerOrder = varRovWinPerOrder;
    }

    public double getVarRovFailPerOrder() {
        return varRovFailPerOrder;
    }

    public void setVarRovFailPerOrder(double varRovFailPerOrder) {
        this.varRovFailPerOrder = varRovFailPerOrder;
    }

    public double getWinsRate() {
        return winsRate;
    }

    public void setWinsRate(double winsRate) {
        this.winsRate = winsRate;
    }

    public double getMeanFund() {
        return meanFund;
    }

    public void setMeanFund(double meanFund) {
        this.meanFund = meanFund;
    }

    public double getMeanWinsRate() {
        return meanWinsRate;
    }

    public void setMeanWinsRate(double meanWinsRate) {
        this.meanWinsRate = meanWinsRate;
    }

    public double getMeanWinToLossRate() {
        return meanWinToLossRate;
    }

    public void setMeanWinToLossRate(double meanWinToLossRate) {
        this.meanWinToLossRate = meanWinToLossRate;
    }

    public double getMeanEarningRate() {
        return meanEarningRate;
    }

    public void setMeanEarningRate(double meanEarningRate) {
        this.meanEarningRate = meanEarningRate;
    }

    public double getMeanRovPerOrder() {
        return meanRovPerOrder;
    }

    public void setMeanRovPerOrder(double meanRovPerOrder) {
        this.meanRovPerOrder = meanRovPerOrder;
    }

    public double getMeanWinPerOrder() {
        return meanWinPerOrder;
    }

    public void setMeanWinPerOrder(double meanWinPerOrder) {
        this.meanWinPerOrder = meanWinPerOrder;
    }

    public double getMeanRovFailPerOrder() {
        return meanRovFailPerOrder;
    }

    public void setMeanRovFailPerOrder(double meanRovFailPerOrder) {
        this.meanRovFailPerOrder = meanRovFailPerOrder;
    }

    @Override
    public String toString() {
        return "TradeContextStat{" +
                "fund=" + fund +
                ", cost=" + cost +
                ", winsRate=" + winsRate +
                ", varFund=" + varFund +
                ", varWinsRate=" + varWinsRate +
                ", winCount=" + winCount +
                ", failCount=" + failCount +
                ", varWinToLossRate=" + varWinToLossRate +
                ", varEarningRate=" + varEarningRate +
                ", earingCount=" + earingCount +
                ", lossCount=" + lossCount +
                ", profit=" + profit +
                ", profitPerOrder=" + profitPerOrder +
                ", varRovPerOrder=" + varRovPerOrder +
                ", varRovWinPerOrder=" + varRovWinPerOrder +
                ", varRovFailPerOrder=" + varRovFailPerOrder +
                ", meanFund=" + meanFund +
                ", meanWinsRate=" + meanWinsRate +
                ", meanWinToLossRate=" + meanWinToLossRate +
                ", meanEarningRate=" + meanEarningRate +
                ", meanRovPerOrder=" + meanRovPerOrder +
                ", meanWinPerOrder=" + meanWinPerOrder +
                ", meanRovFailPerOrder=" + meanRovFailPerOrder +
                '}';
    }
}
