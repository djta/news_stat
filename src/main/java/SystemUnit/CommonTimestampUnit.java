package SystemUnit;

import crawl.common.CommonTimestamp;
import crawl.common.CommonTimestampDomain;
import util.HttpUtil;

import static util.Constants.URL_COMMON_TS;
import static util.Constants.URL_TRADE_HISTORY;

/**
 * Created by hzyuyongmao on 2018/4/12.
 *
 * @Description
 */
public class CommonTimestampUnit {
    public static void main(String args[]) {
        boolean isCheck = isCheckCommonTimestamp();
        System.out.println(isCheck);
    }

    public static boolean isCheckCommonTimestamp() {
        long currentTs = System.currentTimeMillis();
        CommonTimestampDomain ctd = CommonTimestamp.getCommonTimeStamp();
        if (ctd == null) {
            return false;
        }
        if (ctd.getData() - currentTs < 3000) {
            return true;
        }
        return false;
    }
}
