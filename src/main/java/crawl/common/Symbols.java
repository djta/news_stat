package crawl.common;

import com.alibaba.fastjson.JSON;
import domain.CurrencysDomain;
import domain.SymbolsDomain;
import domain.SymbolsMainDomain;
import jdbc.impl.CommonDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.MultiThreadService;
import util.Constants;
import util.HttpUtil;


/**
 * Created by hzyuyongmao on 2018/2/26.
 *
 * @Description
 */
public class Symbols {
    private static final Logger logger = LoggerFactory.getLogger(Symbols.class);

    public static void main(String args[]) {
        logger.info("sysmbols init start!!!");
        String result = HttpUtil.doGetData(Constants.URL_SYMBOLS);
        SymbolsMainDomain smd = JSON.parseObject(result, SymbolsMainDomain.class);
        CommonDaoImpl cdi = new CommonDaoImpl();
        for (SymbolsDomain sd : smd.getData()) {
            cdi.insertSymbols(sd);
        }
        logger.info("sysmbols init end!!!");
    }
}
