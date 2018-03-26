package domain.talib;

/**
 * Created by 范志伟 on 2018-03-27.
 */
public class BollingerBandDomain {
    private double upper;
    private double mid;
    private double lower;

    public double getUpper() {
        return upper;
    }

    public void setUpper(double upper) {
        this.upper = upper;
    }

    public double getMid() {
        return mid;
    }

    public void setMid(double mid) {
        this.mid = mid;
    }

    public double getLower() {
        return lower;
    }

    public void setLower(double lower) {
        this.lower = lower;
    }

    @Override
    public String toString() {
        return "BollingerBandDomain{" +
                "upper=" + upper +
                ", mid=" + mid +
                ", lower=" + lower +
                '}';
    }
}
