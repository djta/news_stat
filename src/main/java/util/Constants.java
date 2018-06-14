package util;

import com.google.common.collect.Sets;

import java.util.HashSet;

/**
 * Created by hzyuyongmao on 2018/2/23.
 *
 * @Description
 */
public class Constants {

    public static String ACCESSKEY_ID = "9f4891da-a8639ea6-ff811f7b-c4c3f";
    public static String ACCESSKEY = "3719bf6c-b19716fa-7882d06f-b0b10";
    //    private static final String SERVICE="https://api.huobi.pro";
//    private static final String SERVICE = "https://api.huobipro.com";
    private static final String SERVICE = "https://api.huobi.br.com";
    //

    public static final String URL_MARKET_KLINE = SERVICE + "/market/history/kline?";

    //market depth
    public static final String URL_MARKET_DEPTH = SERVICE + "/market/depth?";

    //trade
    public static final String URL_TRADE = SERVICE + "/market/trade?";
    //trade history
    public static final String URL_TRADE_HISTORY = SERVICE + "/market/history/trade?";
    /*
       common api
     */
    //currencys
    public static final String URL_CURRENCYS = SERVICE + "/v1/common/currencys";
    public static final String URL_SYMBOLS = SERVICE + "/v1/common/symbols";
    public static final String URL_COMMON_TS = SERVICE + "/v1/common/timestamp";
    /*
         Accounts API
     */
    public static final String URL_ACCOUNT_ACCOUNTS = SERVICE + "/v1/account/accounts";
    public static final String URL_ACCOUNT_BALANCE = SERVICE + "/v1/account/accounts/1880317/balance";
    //Params
    public static final String URL_HOSTNAME_GET = "GET\napi.huobipro.com\n";
    public static final String URL_HOSTNAME_POST = "POST\napi.huobipro.com\n";
    //Order
    public static final String URL_ORDER_PLACE = SERVICE + "/v1/order/orders/place";
}
