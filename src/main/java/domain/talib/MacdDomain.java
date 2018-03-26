package domain.talib;

/**
 * Created by 范志伟 on 2018-03-26.
 */
public class MacdDomain {
    private double dif;
    private double dea;

    public double getDif() {
        return dif;
    }

    public void setDif(double dif) {
        this.dif = dif;
    }

    public double getDea() {
        return dea;
    }

    public void setDea(double dea) {
        this.dea = dea;
    }

    @Override
    public String toString() {
        return "MacdDomain{" +
                "dif=" + dif +
                ", dea=" + dea +
                '}';
    }
}
