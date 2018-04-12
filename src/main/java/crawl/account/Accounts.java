package crawl.account;

import util.Constants;
import util.HmacSHA256;
import util.HttpUtil;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by hzyuyongmao on 2018/4/12.
 *
 * @Description
 */
public class Accounts {

    public static void main(String args[]) throws UnsupportedEncodingException {
        System.out.println("OK");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = new Date();
        String dateStr = df.format(date);
        System.out.println(dateStr);
        String rawUrl = "GET\\n" + "api.huobipro.com\\n" + "/v1/account/accounts\\n";

        String rawParams = "?AccessKeyId=" + Constants.ACCESSKEY_ID
                + "&SignatureMethod=HmacSHA256&SignatureVersion=2&Timestamp=" + dateStr;

        String rawUrlUrl = URLEncoder.encode(rawParams, "utf-8");
        System.out.println(rawUrlUrl);
        String signature = HmacSHA256.encodeHmacSHA256(Constants.ACCESSKEY.getBytes(), rawUrl + rawUrlUrl);
        String signatureUri = "";
        try {
            signatureUri = java.net.URLEncoder.encode(signature, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(signatureUri);
        String url = Constants.URL_ACCOUNT_ACCOUNTS + "?" + rawUrlUrl + "&Signature=" + signatureUri;
        System.out.println("url:" + url);
        String result = HttpUtil.doGetData(url);
        System.out.println(result);

    }
}
