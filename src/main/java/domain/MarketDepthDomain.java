package domain;

import java.util.List;

/**
 * Created by hzyuyongmao on 2018/2/26.
 *
 * @Description
 */
public class MarketDepthDomain {
    private long id;//消息id,
    private long ts;//消息生成时间，单位：毫秒,
    private List<List> bids;//买盘,[price(成交价), amount(成交量)], 按price降序,
    private List<List> asks;//卖盘,[price(成交价), amount(成交量)], 按price升序

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

    public List getBids() {
        return bids;
    }

    public void setBids(List bids) {
        this.bids = bids;
    }

    public List getAsks() {
        return asks;
    }

    public void setAsks(List asks) {
        this.asks = asks;
    }

}
