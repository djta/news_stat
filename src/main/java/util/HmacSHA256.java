package util;

/**
 * Created by hzyuyongmao on 2018/3/7.
 *
 * @Description
 */

import java.io.UnsupportedEncodingException;
import java.security.Security;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class HmacSHA256 {

    private static final Base64 base64;

    static {
        base64 = new Base64();
    }

    /**
     * HmacSHA256消息摘要
     *
     * @param data 待做摘要处理的数据
     * @return String 消息摘要
     * @throws UnsupportedEncodingException
     */
    public static String encodeHmacSHA256(byte[] key, String data) {
        // 加入BouncyCastleProvider的支持
        Security.addProvider(new BouncyCastleProvider());
        // 还原密钥，因为密钥是以byte形式为消息传递算法所拥有
        SecretKey secretKey = new SecretKeySpec(key, "HmacSHA256");
        // 实例化Mac
        Mac mac = null;
        try {
            mac = Mac.getInstance(secretKey.getAlgorithm());
            // 初始化Mac
            mac.init(secretKey);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Base64 base64 = SingleBase64.instance;
        // 16进制编码信息摘要
        // return new String(Hex.encode(mac.doFinal(data.getBytes())));
        // base64编码信息摘要
        return new String(base64.encode(mac.doFinal(data.getBytes())));

    }

    public static void main(String args[]) throws Exception {
        String content = "GET\n" + "api.huobipro.com\n" + "/v1/account/accounts/1880317/balance\n" + "AccessKeyId=9f4891da-a8639ea6-ff811f7b-c4c3f&SignatureMethod=HmacSHA256&SignatureVersion=2&Timestamp=2018-04-16T01%3A29%3A49";
        String key = "test";
        String result = encodeHmacSHA256(key.getBytes(), content);
        System.out.println(result);
    }

}