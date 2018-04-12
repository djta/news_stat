package crawl.order;

/**
 * Created by 范志伟 on 2018-04-13.
 */
public enum OrdersTypeEnum {
    //buy-market：市价买, sell-market：市价卖, buy-limit：限价买, sell-limit：限价卖
    buy_Market("buy-market"),
    sell_Market("sell-market"),
    buy_Limit("buy-limit"),
    sell_Limit("sell-limit");

    public String value;

    private OrdersTypeEnum(String value) {
        this.value = value;
    }
}
