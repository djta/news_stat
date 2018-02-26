package domain;

/**
 * Created by hzyuyongmao on 2018/2/26.
 *
 * @Description
 */
public class MarketTradeMainDomain {
    private String status;
    private String ch;
    private long ts;
    private MarketTradeTickDomain tick;

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

    public MarketTradeTickDomain getTick() {
        return tick;
    }

    public void setTick(MarketTradeTickDomain tick) {
        this.tick = tick;
    }
}

