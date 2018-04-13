package util;

import com.sun.deploy.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 范志伟 on 2018-04-12.
 */
public class StringUtil {
    public static void main(String args[]) {
//        System.out.println(getUriString(DateUtil.getUTCCurrentTime()));
        String[] str = {"order-id=1234567890"};
        String parms = accountparmsAsciiSort(str);
        System.out.println(parms);
    }


    public static String getUriString(String str) {
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /*
         参数字典排序
     */
    public static String accountparmsAsciiSort(String[] parms) {
        String accessKeyId = "AccessKeyId=" + Constants.ACCESSKEY_ID;
        String signatureMethod = "SignatureMethod=HmacSHA256";
        String signatureVersion = "SignatureVersion=2";
        String timestamp = "Timestamp=" + getUriString(DateUtil.getUTCCurrentTime());
        String[] paramArray = new String[parms.length + 4];
        paramArray[0] = accessKeyId;
        paramArray[1] = signatureMethod;
        paramArray[2] = signatureVersion;
        paramArray[3] = timestamp;
        for (int i = 0; i < parms.length; i++) {
            paramArray[4 + i] = parms[i];
        }
        Arrays.sort(paramArray);
        String params = StringUtils.join(Arrays.asList(paramArray), "&");
        return params;
    }

    /*
        参数字典排序
    */
    public static String accountparmsAsciiSort() {
        String accessKeyId = "AccessKeyId=" + Constants.ACCESSKEY_ID;
        String signatureMethod = "SignatureMethod=HmacSHA256";
        String signatureVersion = "SignatureVersion=2";
        String timestamp = "Timestamp=" + getUriString(DateUtil.getUTCCurrentTime());
        String[] paramArray = new String[4];
        paramArray[0] = accessKeyId;
        paramArray[1] = signatureMethod;
        paramArray[2] = signatureVersion;
        paramArray[3] = timestamp;
        Arrays.sort(paramArray);
        String params = StringUtils.join(Arrays.asList(paramArray), "&");
        return params;
    }
}
