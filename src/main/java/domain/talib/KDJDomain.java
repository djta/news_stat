package domain.talib;

/**
 * Created by hzyuyongmao on 2018/6/19.
 *
 * @Description
 */
public class KDJDomain {
    private double k;
    private double d;
    private double j;

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
        return "KDJDomain{" +
                "k=" + k +
                ", d=" + d +
                ", j=" + j +
                '}';
    }
}
