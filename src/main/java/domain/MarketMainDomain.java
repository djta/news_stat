package domain;

import java.util.List;

/**
 * Created by hzyuyongmao on 2018/2/23.
 *
 * @Description
 */
public class MarketMainDomain {
    private String status;//请求处理结果:"ok" , "error"
    private String ch;//数据所属的 channel，格式： market.$symbol.kline.$period
    private List<MarketDomain> data;//KLine 数据
    private long ts;//响应生成时间点，单位：毫秒
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

    public List<MarketDomain> getData() {
        return data;
    }

    public void setData(List<MarketDomain> data) {
        this.data = data;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
