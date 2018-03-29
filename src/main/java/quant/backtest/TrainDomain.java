package quant.backtest;

/**
 * Created by hzyuyongmao on 2018/3/29.
 *
 * @Description
 */
public class TrainDomain {
    private double profit;
    private int shortPeriod;
    private int longPeriod;
    private int mid;

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public int getShortPeriod() {
        return shortPeriod;
    }

    public void setShortPeriod(int shortPeriod) {
        this.shortPeriod = shortPeriod;
    }

    public int getLongPeriod() {
        return longPeriod;
    }

    public void setLongPeriod(int longPeriod) {
        this.longPeriod = longPeriod;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    @Override
    public String toString() {
        return "TrainDomain{" +
                "profit=" + profit +
                ", shortPeriod=" + shortPeriod +
                ", longPeriod=" + longPeriod +
                ", mid=" + mid +
                '}';
    }
}
