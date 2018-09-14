package qunat2.wrap.domain;

import qunat2.wrap.PartEnum;

public class SegmentFetureDomain {
    private PartEnum partEnum;
    private double start;
    private double end;

    public PartEnum getPartEnum() {
        return partEnum;
    }

    public void setPartEnum(PartEnum partEnum) {
        this.partEnum = partEnum;
    }

    public double getStart() {
        return start;
    }

    public void setStart(double start) {
        this.start = start;
    }

    public double getEnd() {
        return end;
    }

    public void setEnd(double end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "SegmentFetureDomain{" +
                "partEnum=" + partEnum +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
