package domain.stat;

/**
 * Created by 范志伟 on 2018-03-05.
 */
public class TradeHistoryStatDomain {
    private String symbol;
    private double buyMount;
    private double buyPrice;
    private int buyCount;
    private long ts;//获取结果的ts
    private double sellMount;
    private  double sellPrice;
    private int sellCount;
    private String status;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getBuyMount() {
        return buyMount;
    }

    public void setBuyMount(double buyMount) {
        this.buyMount = buyMount;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public int getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public double getSellMount() {
        return sellMount;
    }

    public void setSellMount(double sellMount) {
        this.sellMount = sellMount;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getSellCount() {
        return sellCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSellCount(int sellCount) {
        this.sellCount = sellCount;
    }

    @Override
    public String toString() {
        return "TradeHistoryStatDomain{" +
                "symbol='" + symbol + '\'' +
                ", buyMount=" + buyMount +
                ", buyPrice=" + buyPrice +
                ", buyCount=" + buyCount +
                ", ts=" + ts +
                ", sellMount=" + sellMount +
                ", sellPrice=" + sellPrice +
                ", sellCount=" + sellCount +
                ", status='" + status + '\'' +
                '}';
    }
}
