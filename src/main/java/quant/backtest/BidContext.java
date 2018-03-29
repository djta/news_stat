package quant.backtest;

/**
 * Created by 范志伟 on 2018-03-29.
 */
public class BidContext {
    private double amount;//拥有币量
    private double fund;//初始化资金
    private double rate = 0.002;//费率；
    private double cost = 0;//手续费
    private int buy = 0;//买次数；
    private int sell = 0;//卖次数；

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

    public BidContext(double fund) {
        this.fund = fund;
    }

    public static void main(String args[]) {

    }

    synchronized public boolean buy(double close) {
        if (fund <= 0) {
            return false;
        }
        cost += fund * rate;
        fund -= fund * rate;
        amount = fund / close;
        buy++;
        fund = 0;
//        System.out.println("buy success:" + amount);
        return true;

    }

    public boolean sell(double close) {
        if (amount <= 0) {
            return false;
        }
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

    @Override
    public String toString() {
        return "BidContext{" +
                "amount=" + amount +
                ", fund=" + fund +
                ", rate=" + rate +
                ", cost=" + cost +
                ", buy=" + buy +
                ", sell=" + sell +
                '}';
    }
}
