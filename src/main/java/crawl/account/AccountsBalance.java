package crawl.account;

import com.alibaba.fastjson.JSON;
import crawl.account.domain.AccountsBalanceDomain;
import util.Constants;
import util.HmacSHA256;
import util.HttpUtil;
import util.StringUtil;

/**
 * Created by 范志伟 on 2018-04-12.
 */
public class AccountsBalance {
    private static String path = "/v1/account/accounts/1880317/balance\n";

    public static void main(String args[]) {

//        String signature = getSignature(path);
//        System.out.println(signature);
//        String rawStr = "?" + StringUtil.accountparmsAsciiSort() + "&Signature=" + signature;
//        String url = Constants.URL_ACCOUNT_BALANCE + rawStr;
//        System.out.println(url);
        System.out.println(getAccountBalanceInfo());
    }

    private static String getSignature(String path) {
        String param = StringUtil.accountparmsAsciiSort();
        String rawStr = Constants.URL_HOSTNAME_GET + path + param;
        return StringUtil.getUriString(HmacSHA256.encodeHmacSHA256(Constants.ACCESSKEY.getBytes(), rawStr));
    }

    public static AccountsBalanceDomain getAccountBalanceInfo() {
        String signature = getSignature(path);
        String rawStr = "?" + StringUtil.accountparmsAsciiSort() + "&Signature=" + signature;
        String url = Constants.URL_ACCOUNT_BALANCE + rawStr;
        String urlData = HttpUtil.doGetData(url);
        AccountsBalanceDomain mmd = JSON.parseObject(urlData, AccountsBalanceDomain.class);
        return mmd;
    }

}
