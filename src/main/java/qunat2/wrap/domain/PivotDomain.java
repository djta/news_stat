package qunat2.wrap.domain;

import qunat2.wrap.PartEnum;
import qunat2.wrap.RankEnum;

public class PivotDomain {
    private String symbol;
    private double gg;
    private double dd;
    private double zg;
    private double zd;
    private RankEnum rankEnum;//级别
    private PartEnum partEnum;//趋势

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getGg() {
        return gg;
    }

    public void setGg(double gg) {
        this.gg = gg;
    }

    public double getDd() {
        return dd;
    }

    public void setDd(double dd) {
        this.dd = dd;
    }

    public double getZg() {
        return zg;
    }

    public void setZg(double zg) {
        this.zg = zg;
    }

    public double getZd() {
        return zd;
    }

    public void setZd(double zd) {
        this.zd = zd;
    }

    public RankEnum getRankEnum() {
        return rankEnum;
    }

    public void setRankEnum(RankEnum rankEnum) {
        this.rankEnum = rankEnum;
    }

    public PartEnum getPartEnum() {
        return partEnum;
    }

    public void setPartEnum(PartEnum partEnum) {
        this.partEnum = partEnum;
    }

    @Override
    public String toString() {
        return "PivotDomain{" +
                "symbol='" + symbol + '\'' +
                ", gg=" + gg +
                ", dd=" + dd +
                ", zg=" + zg +
                ", zd=" + zd +
                ", rankEnum=" + rankEnum +
                ", partEnum=" + partEnum +
                '}';
    }
}
