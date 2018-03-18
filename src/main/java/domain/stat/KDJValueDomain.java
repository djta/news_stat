package domain.stat;

/**
 * Created by 范志伟 on 2018-03-18.
 */
public class KDJValueDomain {
    private String symbol;
    private double k;
    private double d;
    private double j;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public double getJ() {
        return j;
    }

    public void setJ(double j) {
        this.j = j;
    }

    @Override
    public String toString() {
        return "KDJValueDomain{" +
                "symbol='" + symbol + '\'' +
                ", k=" + k +
                ", d=" + d +
                ", j=" + j +
                '}';
    }
}
