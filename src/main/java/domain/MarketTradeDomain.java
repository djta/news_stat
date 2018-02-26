package domain;

import java.math.BigDecimal;

/**
 * Created by hzyuyongmao on 2018/2/26.
 *
 * @Description
 */
public class MarketTradeDomain {

    private BigDecimal id;//成交id,
    private double price;//成交价钱,
    private double amount;//成交量,
    private String direction;//主动成交方向,
    private long ts;//成交时间

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    @Override
    public String toString() {
        return "MarketTradeDomain{" +
                "id=" + id +
                ", price=" + price +
                ", amount=" + amount +
                ", direction='" + direction + '\'' +
                ", ts=" + ts +
                '}';
    }
}
