package crawl.common;

import com.alibaba.fastjson.JSON;
import domain.CurrencysDomain;
import domain.SymbolsDomain;
import domain.SymbolsMainDomain;
import jdbc.impl.CommonDaoImpl;
import util.Constants;
import util.HttpUtil;

import java.util.logging.Logger;

/**
 * Created by hzyuyongmao on 2018/2/26.
 *
 * @Description
 */
public class Symbols {
    private static final Logger LOGGER = Logger.getLogger("Symbols.class");

    public static void main(String args[]) {
        LOGGER.info("sysmbols init start!!!");
        String result = HttpUtil.doGetData(Constants.URL_SYMBOLS);
        SymbolsMainDomain smd = JSON.parseObject(result, SymbolsMainDomain.class);
        CommonDaoImpl cdi = new CommonDaoImpl();
        for (SymbolsDomain sd : smd.getData()) {
            cdi.insertSymbols(sd);
        }
        LOGGER.info("sysmbols init end!!!");
    }
}
