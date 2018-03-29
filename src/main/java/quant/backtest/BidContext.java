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
        System.out.println("buy success:" + amount);
        return true;

    }

    public boolean sell(double close) {
        if (amount <= 0) {
            return false;
        }
        cost += amount * rate * close;
        amount -= amount * rate;
        fund = close * amount;
        System.out.println("sell success:" + fund);
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
