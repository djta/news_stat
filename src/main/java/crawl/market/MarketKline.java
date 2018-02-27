package crawl.market;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import domain.SymbolsDomain;
import jdbc.impl.CommonDaoImpl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by hzyuyongmao on 2018/2/26.
 *
 * @Description
 */
public class MarketKline {
    private Cache<String, Set<String>> noUrsCache = CacheBuilder.newBuilder().recordStats().expireAfterWrite(10, TimeUnit.MINUTES).build();

    public static void main(String args[]) {
        CommonDaoImpl cdi = new CommonDaoImpl();
        List<SymbolsDomain> list = cdi.selectSymbols();
        for (SymbolsDomain sd : list) {
            System.out.println(sd);
        }
    }
}
