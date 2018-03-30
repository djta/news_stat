package quant.trade;

/**
 * Created by 范志伟 on 2018-03-29.
 */
public class TradeContext {
    private double amount;//拥有币量
    private double fund;//初始化资金
    private double rate = 0.002;//费率；
    private double cost = 0;//手续费
    private int buy = 0;//买次数；
    private int sell = 0;//卖次数；
    private double buyPrice;//当前价格
    private double sellPrice;//

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

    public static void main(String args[]) {

    }

    synchronized public boolean buy(double close) {
        if (fund <= 0) {
            return false;
        }
        buyPrice=close;
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
        sellPrice=close;
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

    public  void stoplossUnit(double close,double stopLossPoint){
       if(amount>0&&close<buyPrice*stopLossPoint){
             sell(close);
       }
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
                '}';
    }
}
