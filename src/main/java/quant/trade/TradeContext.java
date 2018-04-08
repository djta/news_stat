package quant.trade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 范志伟 on 2018-03-29.
 */
public class TradeContext {
    private static final Logger logger = LoggerFactory.getLogger(TradeContext.class);
    private double amount;//拥有币量
    private double fund;//初始化资金
    private double rate = 0.002;//费率；
    private double cost = 0;//手续费
    private int buy = 0;//买次数；
    private int sell = 0;//卖次数；
    private double buyPrice;//买入价格
    private double sellPrice;//
    private double currentPrice;
    private double winsRate;//获胜率
    private int winCount;
    private int failCount;
    private List<TradeDomain> tradeDomains = new ArrayList<TradeDomain>();
    private TradeDomain tradeDomain;
    private double winToLossRate;//赢亏比

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getFund() {
        return fund;
    }

    public void setFund(double fund) {
        this.fund = fund;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getBuy() {
        return buy;
    }

    public void setBuy(int buy) {
        this.buy = buy;
    }

    public int getSell() {
        return sell;
    }

    public void setSell(int sell) {
        this.sell = sell;
    }

    public TradeContext(double fund) {
        this.fund = fund;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public List<TradeDomain> getTradeDomains() {
        return tradeDomains;
    }

    public void setTradeDomains(List<TradeDomain> tradeDomains) {
        this.tradeDomains = tradeDomains;
    }

    public double getWinsRate() {
        return winsRate;
    }

    public void setWinsRate(double winsRate) {
        this.winsRate = winsRate;
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

    public TradeDomain getTradeDomain() {
        return tradeDomain;
    }

    public void setTradeDomain(TradeDomain tradeDomain) {
        this.tradeDomain = tradeDomain;
    }

    public static void main(String args[]) {

    }

    synchronized public boolean buy(double close) {
        if (fund <= 0) {
            return false;
        }
        buyPrice = close;
        cost += fund * rate;
        fund -= fund * rate;
        amount = fund / close;
        buy++;
        fund = 0;
//        System.out.println("buy success:" + amount);
        return true;

    }

    synchronized public boolean sell(double close) {
        if (amount <= 0) {
            return false;
        }
        sellPrice = close;
        cost += amount * rate * close;
        amount -= amount * rate;
        fund = close * amount;
//        System.out.println("sell success:" + fund);
        amount = 0;
        sell++;
        return true;
    }

    public double profit() {
        return fund;
    }


    public void stoplossUnit(double close, double stopLossPoint) {
        if (amount > 0 && close < buyPrice * stopLossPoint) {
            sell(close);
        }
    }

     /*
       跟踪止损
     */

    public void stopProfit(double close) {

        if (currentPrice * 0.97 < close) {
            sell(close);
        }
        currentPrice = close;
    }

    @Override
    public String toString() {
        return "TradeContext{" +
                "amount=" + amount +
                ", fund=" + fund +
                ", rate=" + rate +
                ", cost=" + cost +
                ", buy=" + buy +
                ", sell=" + sell +
                ", buyPrice=" + buyPrice +
                ", sellPrice=" + sellPrice +
                ", currentPrice=" + currentPrice +
                ", winsRate=" + winsRate +
                ", winCount=" + winCount +
                ", failCount=" + failCount +
                ", tradeDomains=" + tradeDomains +
                ", tradeDomain=" + tradeDomain +
                ", winToLossRate=" + winToLossRate +
                '}';
    }


    public void bull(double close) {
        double cost = this.fund * rate;
        double fund = this.fund - cost;
        tradeDomain = new TradeDomain(fund);
        tradeDomain.setBuyPrice(close);
        tradeDomain.setCost(cost * 2);
        tradeDomain.setAmount(fund / close);
    }

    public void bear(double close) {
        if (tradeDomain == null) {
            return;
        }
        double amount = tradeDomain.getAmount();
        amount -= amount * rate;
        double fund = amount * close;
        this.fund = fund;
        tradeDomain.setSellPrice(close);
        tradeDomain.setFund(fund);
        tradeDomains.add(tradeDomain);
        tradeDomain = null;
    }

    public void resultStat() {
        int tradeCount = tradeDomains.size();
        double win = 0;
        double loss = 0;
        for (TradeDomain tradeDomain : tradeDomains) {
            if (tradeDomain.getBuyPrice() <= tradeDomain.getSellPrice()) {
                win += tradeDomain.getSellPrice() - tradeDomain.getBuyPrice();
                winCount++;
            } else {
                loss += tradeDomain.getBuyPrice() - tradeDomain.getSellPrice();
                failCount++;
            }
            this.cost += tradeDomain.getCost();
        }
        winsRate = (double) winCount / tradeCount;
        winToLossRate = (win / winCount) / (loss / failCount);
    }


}
