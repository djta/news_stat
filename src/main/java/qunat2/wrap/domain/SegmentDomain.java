package qunat2.wrap.domain;

import qunat2.wrap.PartEnum;

public class SegmentDomain {
    private long startId;
    private long endId;
    private double startSeg;
    private double endSeg;
    private PartEnum segEnum;
    private String symbol;

    public long getStartId() {
        return startId;
    }

    public void setStartId(long startId) {
        this.startId = startId;
    }

    public long getEndId() {
        return endId;
    }

    public void setEndId(long endId) {
        this.endId = endId;
    }

    public double getStartSeg() {
        return startSeg;
    }

    public void setStartSeg(double startSeg) {
        this.startSeg = startSeg;
    }

    public double getEndSeg() {
        return endSeg;
    }

    public void setEndSeg(double endSeg) {
        this.endSeg = endSeg;
    }

    public PartEnum getSegEnum() {
        return segEnum;
    }

    public void setSegEnum(PartEnum segEnum) {
        this.segEnum = segEnum;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "SegmentDomain{" +
                "startId=" + startId +
                ", endId=" + endId +
                ", startSeg=" + startSeg +
                ", endSeg=" + endSeg +
                ", segEnum=" + segEnum +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
