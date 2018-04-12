package crawl.common;

import com.alibaba.fastjson.JSON;
import domain.CurrencysDomain;
import util.Constants;
import util.HttpUtil;

/**
 * Created by hzyuyongmao on 2018/4/12.
 *
 * @Description
 */
public class CommonTimestamp {
    public static void main(String args[]) {
        String result = HttpUtil.doGetData(Constants.URL_COMMON_TS);
        System.out.println(result);
        CommonTimestampDomain mmd = JSON.parseObject(result, CommonTimestampDomain.class);
        System.out.println(mmd);
    }

    public static CommonTimestampDomain getCommonTimeStamp() {
        String result = HttpUtil.doGetData(Constants.URL_COMMON_TS);
        CommonTimestampDomain mmd = JSON.parseObject(result, CommonTimestampDomain.class);
        return mmd;
    }
}
