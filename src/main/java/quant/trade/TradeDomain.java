package quant.trade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by hzyuyongmao on 2018/4/8.
 *
 * @Description
 */
public class TradeDomain {
    private static final Logger logger = LoggerFactory.getLogger(TradeDomain.class);
    private double amount;//拥有币量
    private double fund;//初始化资金
    private double rate = 0.002;//费率；
    private double cost;//手续费
    private double buyPrice;//买入价格
    private double sellPrice;//
    private boolean isWin;//多空单
    private double diff;//卖买差
    private double netincome;//净利润
    private boolean isIncomeWin;//净值

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

    public TradeDomain(double fund) {
        this.fund = fund;
    }

    public boolean isWin() {
        return isWin;
    }

    public void setWin(boolean win) {
        isWin = win;
    }

    public double getDiff() {
        return diff;
    }

    public void setDiff(double diff) {
        this.diff = diff;
    }

    public double getNetincome() {
        return netincome;
    }

    public void setNetincome(double netincome) {
        this.netincome = netincome;
    }

    public boolean isIncomeWin() {
        return isIncomeWin;
    }

    public void setIncomeWin(boolean incomeWin) {
        isIncomeWin = incomeWin;
    }

    @Override
    public String toString() {
        return "TradeDomain{" +
                "amount=" + amount +
                ", fund=" + fund +
                ", rate=" + rate +
                ", cost=" + cost +
                ", buyPrice=" + buyPrice +
                ", sellPrice=" + sellPrice +
                ", isWin=" + isWin +
                ", diff=" + diff +
                ", netincome=" + netincome +
                ", isIncomeWin=" + isIncomeWin +
                '}';
    }
}
