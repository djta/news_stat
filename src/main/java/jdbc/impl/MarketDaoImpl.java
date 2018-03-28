package jdbc.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import domain.MarketDomain;
import jdbc.Dao.MarketDao;
import jdbc.JdbcInital;
import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by hzyuyongmao on 2018/2/23.
 *
 * @Description
 */
public class MarketDaoImpl extends JdbcInital implements MarketDao {
    public static SqlMapClient sqlMapClient = null;

    static {
        try {
            Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
            sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertMarket(MarketDomain md, String queryId) {
        try {
            sqlMapClient.insert(queryId, md);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<MarketDomain> getKlineData(String symbol) {
        List<MarketDomain> list = null;
        try {
            list = sqlMapClient.queryForList("getKlineData", symbol);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
