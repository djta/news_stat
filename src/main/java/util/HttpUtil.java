package util;

/**
 * Created by hzyuyongmao on 2018/2/23.
 *
 * @Description
 */

import com.alibaba.fastjson.JSON;

import domain.MarketDomain;
import domain.MarketMainDomain;
import org.apache.http.Header;

import org.apache.http.HttpEntity;

import org.apache.http.HttpResponse;

import org.apache.http.HttpStatus;

import org.apache.http.client.ClientProtocolException;

import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpGet;

import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.util.EntityUtils;

public class HttpUtil {
    private static final String AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36";
    private static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
    private static final String LANG = "zh-cn";
    //httpClient
    private static HttpClient httpClient = new DefaultHttpClient();

    static{
        SSLSocketFactory.getSocketFactory().setHostnameVerifier(new AllowAllHostnameVerifier());
    }


    public static String doGet() {
        //httpClient

        HttpClient httpClient = new DefaultHttpClient();

        // get method
        HttpGet httpGet = new HttpGet("https://api.huobi.pro/market/history/kline?period=1min&size=1&symbol=ruffusdt");

        // set header
        httpGet.setHeader("User-Agent", AGENT);
        httpGet.setHeader("Content-Type", CONTENT_TYPE);
        httpGet.setHeader("Accept-Language", LANG);

        //response
        HttpResponse response = null;
        try {
            SSLSocketFactory.getSocketFactory().setHostnameVerifier(new AllowAllHostnameVerifier());
            response = httpClient.execute(httpGet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //get response into String
        String temp = "";
        try {
            HttpEntity entity = response.getEntity();
            temp = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return temp;
    }

    public static String doGetMarket(String symbol, String period, int size) {


        // get method
        HttpGet httpGet = new HttpGet("https://api.huobi.pro/market/history/kline?period=" + period + "&size="+size+"&symbol=" + symbol);

        // set header
        httpGet.setHeader("User-Agent", AGENT);
        httpGet.setHeader("Content-Type", CONTENT_TYPE);
        httpGet.setHeader("Accept-Language", LANG);

        //response
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //get response into String
        String temp = "";
        try {
            HttpEntity entity = response.getEntity();
            temp = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
        }

        return temp;
    }

    public static String doGetData(String url) {
        // get method
        HttpGet httpGet = new HttpGet(url);
        // set header
        httpGet.setHeader("User-Agent", AGENT);
        httpGet.setHeader("Content-Type", CONTENT_TYPE);
        httpGet.setHeader("Accept-Language", LANG);
        //response
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //get response into String
        String temp = "";
        try {
            HttpEntity entity = response.getEntity();
            temp = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
        }

        return temp;
    }

    public static void main(String args[]) {
        String result = doGet();
        System.out.println(result);
        MarketMainDomain mmd = JSON.parseObject(result, MarketMainDomain.class);
        for (MarketDomain md : mmd.getData()) {
            System.out.println(md.getId());
        }
        System.out.println(Constants.Period.day.period);
        System.out.println(Constants.Period.min_1.period);
        Constants.Period[] periods = Constants.Period.values();
        for (Constants.Period p : periods) {
            System.out.println(p.period);
        }
    }
}
