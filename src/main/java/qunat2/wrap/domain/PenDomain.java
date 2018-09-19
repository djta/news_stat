package qunat2.wrap.domain;

import qunat2.wrap.PartEnum;

public class PenDomain {
    private long startId;
    private long endId;
    private double startPen;
    private double endPen;
    private PartEnum penEnum;
    private String symbol;
    private boolean isStart;
    private boolean isEnd;
    private boolean isFinish;


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

    public double getStartPen() {
        return startPen;
    }

    public void setStartPen(double startPen) {
        this.startPen = startPen;
    }

    public double getEndPen() {
        return endPen;
    }

    public void setEndPen(double endPen) {
        this.endPen = endPen;
    }

    public PartEnum getPenEnum() {
        return penEnum;
    }

    public void setPenEnum(PartEnum penEnum) {
        this.penEnum = penEnum;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    @Override
    public String toString() {
        return "PenDomain{" +
                "startId=" + startId +
                ", endId=" + endId +
                ", startPen=" + startPen +
                ", endPen=" + endPen +
                ", penEnum=" + penEnum +
                ", symbol='" + symbol + '\'' +
                ", isStart=" + isStart +
                ", isEnd=" + isEnd +
                ", isFinish=" + isFinish +
                '}';
    }
}
