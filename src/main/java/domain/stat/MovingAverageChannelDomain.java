package domain.stat;

/**
 * Created by 范志伟 on 2018-03-18.
 */
public class MovingAverageChannelDomain {
    private String symbol;
    private double up;//区间上限
    private double down;//区间下限

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getUp() {
        return up;
    }

    public void setUp(double up) {
        this.up = up;
    }

    public double getDown() {
        return down;
    }

    public void setDown(double down) {
        this.down = down;
    }

    @Override
    public String toString() {
        return "MovingAverageChannelDomain{" +
                "symbol='" + symbol + '\'' +
                ", up=" + up +
                ", down=" + down +
                '}';
    }
}
