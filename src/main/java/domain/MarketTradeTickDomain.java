package domain;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by hzyuyongmao on 2018/2/26.
 *
 * @Description
 */
public class MarketTradeTickDomain {

    private long id;//消息id,
    private long ts;//最新成交时间,
    private List<MarketTradeDomain> data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public List<MarketTradeDomain> getData() {
        return data;
    }

    public void setData(List<MarketTradeDomain> data) {
        this.data = data;
    }
}
