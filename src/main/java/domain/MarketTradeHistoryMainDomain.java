package domain;

import java.util.List;

/**
 * Created by hzyuyongmao on 2018/2/26.
 *
 * @Description
 */
public class MarketTradeHistoryMainDomain {
    private String status;
    private String ch;
    private long ts;
    private List<MarketTradeTickDomain> data;
    private String symbol;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public List<MarketTradeTickDomain> getData() {
        return data;
    }

    public void setData(List<MarketTradeTickDomain> data) {
        this.data = data;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "MarketTradeHistoryMainDomain{" +
                "status='" + status + '\'' +
                ", ch='" + ch + '\'' +
                ", ts=" + ts +
                ", data=" + data +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
