package crawl.order;

import com.alibaba.fastjson.JSON;
import crawl.account.domain.AccountsBalanceDomain;
import crawl.order.domain.OrderResultDomain;
import crawl.order.domain.OrdersPlaceDomain;
import util.Constants;
import util.HmacSHA256;
import util.HttpUtil;
//import util.StringUtil;

/**
 * Created by 范志伟 on 2018-04-13.
 */
public class OrdersPlace {
    private static String path = "/v1/order/orders/place\n";

    public static void main(String args[]) {
        //挂单出现error,考虑是ts时间太超前的缘故，考虑多次提交。
//        OrdersPlaceDomain opd = new OrdersPlaceDomain("10", "0.001", "zilusdt", OrdersTypeEnum.buy_Limit.value);
//        OrderResultDomain resultDomain = orderOrdersPlace(opd);
//        System.out.println(resultDomain);
    }

//    private static String getSignature(String path) {
//        String param = StringUtil.accountparmsAsciiSort();
//        String rawStr = Constants.URL_HOSTNAME_POST + path + param;
//        System.out.println(rawStr);
//        return StringUtil.getUriString(HmacSHA256.encodeHmacSHA256(Constants.ACCESSKEY.getBytes(), rawStr));
//    }
//
//    public static OrderResultDomain orderOrdersPlace(OrdersPlaceDomain opd) {
//        String signature = getSignature(path);
//        System.out.println(signature);
//        String rawStr = "?" + StringUtil.accountparmsAsciiSort() + "&Signature=" + signature;
//        String url = Constants.URL_ORDER_PLACE + rawStr;
//        String result = HttpUtil.doPostData(url, opd.toString());
//        OrderResultDomain orderResultDomain = JSON.parseObject(result, OrderResultDomain.class);
//        return orderResultDomain;
//    }
}
