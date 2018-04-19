package jdbc.Dao;

import domain.MarketDomain;

import java.util.List;

/**
 * Created by hzyuyongmao on 2018/2/23.
 *
 * @Description
 */
public interface MarketDao {
    public void insertMarket(MarketDomain md, String queryId);

    public List<MarketDomain> getKlineData(String symbol);

    public List<MarketDomain> getKlineDataOnline(String symbol);

    public List<String> getSymbols();
}
