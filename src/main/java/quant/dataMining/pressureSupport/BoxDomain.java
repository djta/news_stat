package quant.dataMining.pressureSupport;

public class BoxDomain {
    private double meidan;
    private double q1;
    private double q3;

    public double getMeidan() {
        return meidan;
    }

    public void setMeidan(double meidan) {
        this.meidan = meidan;
    }

    public double getQ1() {
        return q1;
    }

    public void setQ1(double q1) {
        this.q1 = q1;
    }

    public double getQ3() {
        return q3;
    }

    public void setQ3(double q3) {
        this.q3 = q3;
    }

    @Override
    public String toString() {
        return "BoxDomain{" +
                "meidan=" + meidan +
                ", q1=" + q1 +
                ", q3=" + q3 +
                '}';
    }
}
