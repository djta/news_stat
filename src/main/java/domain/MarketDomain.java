package domain;

/**
 * Created by hzyuyongmao on 2018/2/23.
 *
 * @Description
 */
public class MarketDomain {
    private long id;// K线id(时间戳)
    private double amount;//成交量
    private long count;//成交笔数
    private double open;//开盘价
    private double close;//收盘价,当K线为最晚的一根时，是最新成交价
    private double low;//最低价
    private double high;//最高价
    private double vol;//成交额, 即 sum(每一笔成交价 * 该笔的成交量)
    private String symbol;//币名;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getVol() {
        return vol;
    }

    public void setVol(double vol) {
        this.vol = vol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    @Override
    public String toString() {
        return "MarketDomain{" +
                "id=" + id +
                ", amount=" + amount +
                ", count=" + count +
                ", open=" + open +
                ", close=" + close +
                ", low=" + low +
                ", high=" + high +
                ", vol=" + vol +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
