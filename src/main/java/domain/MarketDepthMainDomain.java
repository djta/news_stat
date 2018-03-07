package domain;

/**
 * Created by hzyuyongmao on 2018/2/26.
 *
 * @Description
 */
public class MarketDepthMainDomain {
    private String status;//"ok" 或者 "error"
    private String ch;//数据所属的 channel，格式： market.$symbol.depth.$type
    private long ts;//响应生成时间点，单位：毫秒
    private MarketDepthDomain tick;//Depth 数据
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

    public MarketDepthDomain getTick() {
        return tick;
    }

    public void setTick(MarketDepthDomain tick) {
        this.tick = tick;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "MarketDepthMainDomain{" +
                "status='" + status + '\'' +
                ", ch='" + ch + '\'' +
                ", ts=" + ts +
                ", tick=" + tick +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
