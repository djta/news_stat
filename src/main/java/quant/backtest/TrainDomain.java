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
    private int buy;
    private int sell;

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

    @Override
    public String toString() {
        return "TrainDomain{" +
                "profit=" + profit +
                ", shortPeriod=" + shortPeriod +
                ", longPeriod=" + longPeriod +
                ", mid=" + mid +
                ", buy=" + buy +
                ", sell=" + sell +
                '}';
    }
}
