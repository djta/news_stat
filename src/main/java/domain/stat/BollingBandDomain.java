package domain.stat;

/**
 * Created by 范志伟 on 2018-03-18.
 */
public class BollingBandDomain {
    private String symbol;
    private double upwer;//上轨
    private double medium;//中轨
    private double lower;//下轨

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getUpwer() {
        return upwer;
    }

    public void setUpwer(double upwer) {
        this.upwer = upwer;
    }

    public double getMedium() {
        return medium;
    }

    public void setMedium(double medium) {
        this.medium = medium;
    }

    public double getLower() {
        return lower;
    }

    public void setLower(double lower) {
        this.lower = lower;
    }

    @Override
    public String toString() {
        return "BollingBandDomain{" +
                "symbol='" + symbol + '\'' +
                ", upwer=" + upwer +
                ", medium=" + medium +
                ", lower=" + lower +
                '}';
    }
}
