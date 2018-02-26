package crawl.market;

import domain.SymbolsDomain;
import jdbc.impl.CommonDaoImpl;

import java.util.List;

/**
 * Created by hzyuyongmao on 2018/2/26.
 *
 * @Description
 */
public class MarketKline {
    public static void main(String args[]) {
        CommonDaoImpl cdi = new CommonDaoImpl();
        List<SymbolsDomain> list = cdi.selectSymbols();
        for (SymbolsDomain sd : list) {
            System.out.println(sd);
        }
    }
}
