package crawl.order.domain;

import crawl.order.OrdersTypeEnum;

/**
 * Created by 范志伟 on 2018-04-13.
 */
public class OrdersPlaceDomain {
    private String accountId = "1880317";
    private String amount;//限价单表示下单数量，市价买单时表示买多少钱，市价卖单时表示卖多少币
    private String price="";//下单价格，市价单不传该参数
    private String source = "api";
    private String symbol;
    private String type;//订单类型,buy-market：市价买, sell-market：市价卖, buy-limit：限价买, sell-limit：限价卖

    public OrdersPlaceDomain(String amount, String symbol, String type) {
        this.amount = amount;
        this.symbol = symbol;
        this.type = type;
    }

    public OrdersPlaceDomain(String amount, String price, String symbol, String type) {
        this.amount = amount;
        this.price = price;
        this.symbol = symbol;
        this.type = type;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSource() {
        return source;
    }


    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "{ \"account-id\":\"" + accountId + "\", \"amount\":\"" + amount + "\", \"price\":\"" + price + "\", \"source\":\"" + source + "\", \"symbol\":\"" + symbol + "\", \"type\":\"" + type + "\"}";
    }

    public static void main(String args[]) {
        OrdersPlaceDomain opd = new OrdersPlaceDomain("100","0.001", "zilusdt", OrdersTypeEnum.buy_Limit.value);
        System.out.println(opd);
    }
}
