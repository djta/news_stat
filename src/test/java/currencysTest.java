import com.alibaba.fastjson.JSON;
import domain.CurrencysDomain;
import domain.MarketMainDomain;
import util.HttpUtil;

/**
 * Created by hzyuyongmao on 2018/2/26.
 *
 * @Description
 */
public class currencysTest {
    public static void main(String args[]) {
        String url = "https://api.huobi.pro/v1/common/currencys";
        String result = HttpUtil.doGetData(url);
        System.out.println(result);
        CurrencysDomain mmd = JSON.parseObject(result, CurrencysDomain.class);
        System.out.println(mmd.getData().toString());
    }
}
