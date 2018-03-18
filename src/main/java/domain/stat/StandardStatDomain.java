package domain.stat;

/**
 * Created by 范志伟 on 2018-03-18.
 */
public class StandardStatDomain {
    private String symbol;
    private double lowSum;
    private double highSum;
    private double openSum;
    private double closeSum;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getLowSum() {
        return lowSum;
    }

    public void setLowSum(double lowSum) {
        this.lowSum = lowSum;
    }

    public double getHighSum() {
        return highSum;
    }

    public void setHighSum(double highSum) {
        this.highSum = highSum;
    }

    public double getOpenSum() {
        return openSum;
    }

    public void setOpenSum(double openSum) {
        this.openSum = openSum;
    }

    public double getCloseSum() {
        return closeSum;
    }

    public void setCloseSum(double closeSum) {
        this.closeSum = closeSum;
    }

    @Override
    public String toString() {
        return "StandardStatDomain{" +
                "symbol='" + symbol + '\'' +
                ", lowSum=" + lowSum +
                ", highSum=" + highSum +
                ", openSum=" + openSum +
                ", closeSum=" + closeSum +
                '}';

    }
}
