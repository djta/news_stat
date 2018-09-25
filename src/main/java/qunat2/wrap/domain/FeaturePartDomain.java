package qunat2.wrap.domain;

import qunat2.wrap.PartEnum;

public class FeaturePartDomain {

    private PartEnum partEnum;//分型
    private int partIndex;
    private boolean isGap;//分型第一、第二元素是否有缺口
    private String symbol;
    private boolean isPart;//是否有分型
    private PartEnum segmentEnum;//线段

    public PartEnum getPartEnum() {
        return partEnum;
    }

    public void setPartEnum(PartEnum partEnum) {
        this.partEnum = partEnum;
    }

    public int getPartIndex() {
        return partIndex;
    }

    public void setPartIndex(int partIndex) {
        this.partIndex = partIndex;
    }

    public boolean isGap() {
        return isGap;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setGap(boolean gap) {
        isGap = gap;
    }

    public boolean isPart() {
        return isPart;
    }

    public void setPart(boolean part) {
        isPart = part;
    }

    public PartEnum getSegmentEnum() {
        return segmentEnum;
    }

    public void setSegmentEnum(PartEnum segmentEnum) {
        this.segmentEnum = segmentEnum;
    }

    @Override
    public String toString() {
        return "FeaturePartDomain{" +
                "partEnum=" + partEnum +
                ", partIndex=" + partIndex +
                ", isGap=" + isGap +
                ", symbol='" + symbol + '\'' +
                ", isPart=" + isPart +
                ", segmentEnum=" + segmentEnum +
                '}';
    }
}
