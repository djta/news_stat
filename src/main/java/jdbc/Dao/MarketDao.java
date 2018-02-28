package jdbc.Dao;

import domain.MarketDomain;

/**
 * Created by hzyuyongmao on 2018/2/23.
 *
 * @Description
 */
public interface MarketDao {
    public void insertMarket(MarketDomain md, String queryId);
}
