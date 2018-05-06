package domain.talib;

public class StochDomain {
    private double slowK;
    private double slowD;

    public double getSlowK() {
        return slowK;
    }

    public void setSlowK(double slowK) {
        this.slowK = slowK;
    }

    public double getSlowD() {
        return slowD;
    }

    public void setSlowD(double slowD) {
        this.slowD = slowD;
    }

    @Override
    public String toString() {
        return "StorchDomain{" +
                "slowK=" + slowK +
                ", slowD=" + slowD +
                '}';
    }
}
