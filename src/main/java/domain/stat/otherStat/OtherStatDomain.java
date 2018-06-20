package domain.stat.otherStat;

import java.util.List;

/**
 * Created by hzyuyongmao on 2018/6/20.
 *
 * @Description
 */
public class OtherStatDomain {
    private String symbol;
    private double amountPer1Min;//5日每分钟量
    private List<Double> amplitudeList;//振幅
    private List<Double> amountLinearRegSlopeList;//量回归斜率
    private double entrustRatio;//委比
    private double entrustSubtract;//委差

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getAmountPer1Min() {
        return amountPer1Min;
    }

    public void setAmountPer1Min(double amountPer1Min) {
        this.amountPer1Min = amountPer1Min;
    }

    public List<Double> getAmplitudeList() {
        return amplitudeList;
    }

    public void setAmplitudeList(List<Double> amplitudeList) {
        this.amplitudeList = amplitudeList;
    }

    public List<Double> getAmountLinearRegSlopeList() {
        return amountLinearRegSlopeList;
    }

    public void setAmountLinearRegSlopeList(List<Double> amountLinearRegSlopeList) {
        this.amountLinearRegSlopeList = amountLinearRegSlopeList;
    }

    public double getEntrustRatio() {
        return entrustRatio;
    }

    public void setEntrustRatio(double entrustRatio) {
        this.entrustRatio = entrustRatio;
    }

    public double getEntrustSubtract() {
        return entrustSubtract;
    }

    public void setEntrustSubtract(double entrustSubtract) {
        this.entrustSubtract = entrustSubtract;
    }

    @Override
    public String toString() {
        return "OtherStatDomain{" +
                "symbol='" + symbol + '\'' +
                ", amountPer1Min=" + amountPer1Min +
                ", amplitudeList=" + amplitudeList +
                ", amountLinearRegSlopeList=" + amountLinearRegSlopeList +
                ", entrustRatio=" + entrustRatio +
                ", entrustSubtract=" + entrustSubtract +
                '}';
    }
}
