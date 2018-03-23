package quant.SelectSymbolUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import domain.MarketDomain;
import indicator.IndicatorUnit;
import quant.constant.SelectSignConstant;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by hzyuyongmao on 2018/3/23.
 *
 * @Description 选取模块构建主方法
 */
public class SelectSymbolContext {
    //候选symbol对
    public static Cache<String, Long> symbolsCandidateCache = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.HOURS).build();

    /*
        @param Map<Symbol,Map<ts,MarketDomain>>
     */
    public void symbolSelectMain(SelectSymbolUnit ssu, Map<String, Map<Long, MarketDomain>> klineData) {

        for (Map.Entry<String, Map<Long, MarketDomain>> entry : klineData.entrySet()) {
            String symbol = entry.getKey();
            Map<Long, MarketDomain> klineMarketMap = entry.getValue();
            SelectSignConstant selectSign = ssu.getSelectSymbol(klineMarketMap);
            if (selectSign == SelectSignConstant.HIT) {
                symbolsCandidateCache.put(symbol, System.currentTimeMillis());
            }
        }

    }

    public static void main(String args[]) {

    }
}
