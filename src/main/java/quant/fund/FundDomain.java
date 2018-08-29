package quant.fund;

public class FundDomain {
    private double kailiResult;
    private double expect;//期望

    public double getKailiResult() {
        return kailiResult;
    }

    public void setKailiResult(double kailiResult) {
        this.kailiResult = kailiResult;
    }

    public double getExpect() {
        return expect;
    }

    public void setExpect(double expect) {
        this.expect = expect;
    }

    @Override
    public String toString() {
        return "FundDomain{" +
                "kailiResult=" + kailiResult +
                ", expect=" + expect +
                '}';
    }
}
