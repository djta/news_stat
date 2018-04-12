package quant.train;

/**
 * Created by hzyuyongmao on 2018/4/12.
 *
 * @Description
 */
public class TrainDomain {
    private int period;
    private double fund;
    private double winsRate;//获胜率
    private int winCount;
    private int failCount;
    private double winToLossRate;//赢亏比;
    private double cost;


    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public double getFund() {
        return fund;
    }

    public void setFund(double fund) {
        this.fund = fund;
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

    public double getWinToLossRate() {
        return winToLossRate;
    }

    public void setWinToLossRate(double winToLossRate) {
        this.winToLossRate = winToLossRate;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "TrainDomain{" +
                "period=" + period +
                ", fund=" + fund +
                ", winsRate=" + winsRate +
                ", winCount=" + winCount +
                ", failCount=" + failCount +
                ", winToLossRate=" + winToLossRate +
                ", cost=" + cost +
                '}';
    }
}