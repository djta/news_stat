package crawl.common;

import com.alibaba.fastjson.JSON;
import domain.CurrencysDomain;
import util.Constants;
import util.HttpUtil;

/**
 * Created by hzyuyongmao on 2018/2/26.
 *
 * @Description
 */
public class Currencys {
    public static void main(String args[]) {
        String result = HttpUtil.doGetData(Constants.URL_CURRENCYS);
        System.out.println(result);
        CurrencysDomain mmd = JSON.parseObject(result, CurrencysDomain.class);
        System.out.println(mmd.getData().toString());
    }
}
