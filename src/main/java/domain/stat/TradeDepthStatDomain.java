package domain.stat;

/**
 * Created by 范志伟 on 2018-03-05.
 */
public class TradeDepthStatDomain {
    private String status;
    private long ts;
    private String symbol;
    private double askAmount;
    private double askPrice;
    private double bidAmount;
    private double bidPrice;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public double getAskAmount() {
        return askAmount;
    }

    public void setAskAmount(double askAmount) {
        this.askAmount = askAmount;
    }

    public double getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(double askPrice) {
        this.askPrice = askPrice;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "TradeDepthStatDomain{" +
                "status='" + status + '\'' +
                ", ts=" + ts +
                ", symbol='" + symbol + '\'' +
                ", askAmount=" + askAmount +
                ", askPrice=" + askPrice +
                ", bidAmount=" + bidAmount +
                ", bidPrice=" + bidPrice +
                '}';
    }
}
