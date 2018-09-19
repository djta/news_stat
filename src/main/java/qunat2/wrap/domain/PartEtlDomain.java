package qunat2.wrap.domain;

import qunat2.wrap.PartEnum;

public class PartEtlDomain {
    private double value;
    private long id;
    private String symbol;
    private PartEnum partEnum;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public PartEnum getPartEnum() {
        return partEnum;
    }

    public void setPartEnum(PartEnum partEnum) {
        this.partEnum = partEnum;
    }

    @Override
    public String toString() {
        return "PartEtlDomain{" +
                "value=" + value +
                ", id=" + id +
                ", symbol='" + symbol + '\'' +
                ", partEnum=" + partEnum +
                '}';
    }
}
