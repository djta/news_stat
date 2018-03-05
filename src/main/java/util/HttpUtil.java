package util;

/**
 * Created by hzyuyongmao on 2018/2/23.
 *
 * @Description
 */

import com.alibaba.fastjson.JSON;

import com.sun.org.apache.bcel.internal.generic.NEW;
import domain.MarketDomain;
import domain.MarketMainDomain;
import jdbc.impl.MarketDaoImpl;
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

import javax.net.ssl.SSLContext;
import java.security.NoSuchAlgorithmException;

public class HttpUtil {
    private static final String AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36";
    private static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
    private static final String LANG = "zh-cn";

    //   static {
//       try {
//           SSLContext sc = SSLContext.getInstance("TLS");
//           SSLConnectionSocketFactory sslFactory = new SSLConnectionSocketFactory(sc,SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//       } catch (NoSuchAlgorithmException e) {
//           e.printStackTrace();
//       }
//   }
    static {
        SSLSocketFactory.getSocketFactory().setHostnameVerifier(new AllowAllHostnameVerifier());
    }

    //httpClient
//    private static  HttpClient httpClient = new DefaultHttpClient();

    public static String doGet() {
        //httpClient

        HttpClient httpClient = new DefaultHttpClient();

        // get method
        String url = "https://api.huobi.pro/market/history/kline?period=1min&size=1&symbol=ruffusdt";
        HttpGet httpGet = new HttpGet(url);
        System.out.println(url);
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
            e.printStackTrace();
        }

        return temp;
    }

    public static String doGetMarket(String symbol, String period, int size) {

        HttpClient httpClient = new DefaultHttpClient();
        // get method
        String url = "https://api.huobi.pro/market/history/kline?period=" + period + "&size=" + size + "&symbol=" + symbol;
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

    public static String doGetData(String url) {
//        HttpClient httpClient = new DefaultHttpClient();
        HttpClient httpClient = MySSLSocketFactory.getNewHttpClient();
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
//        String result = doGet();
        String result = doGetData("https://api.huobi.pro/market/history/kline?period=1min&size=2000&symbol=zilusdt");
        System.out.println(result);
        MarketMainDomain mmd = JSON.parseObject(result, MarketMainDomain.class);
        MarketDaoImpl mdi = new MarketDaoImpl();
        for (MarketDomain md : mmd.getData()) {
            md.setSymbol("zilusdt");
            System.out.println(md.getId());
            mdi.insertMarket(md, "kline1min");
        }

    }
}
