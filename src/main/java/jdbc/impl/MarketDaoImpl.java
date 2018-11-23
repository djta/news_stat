package jdbc.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import domain.MarketDomain;
import jdbc.Dao.MarketDao;
import jdbc.JdbcInital;
import org.apache.ibatis.io.Resources;
import qunat2.wrap.domain.PartDomain;
import qunat2.wrap.domain.PenDomain;
import qunat2.wrap.domain.SegmentDomain;

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

    public void insertPartMarket(PartDomain md, String queryId) {
        try {
            sqlMapClient.insert(queryId, md);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertPenMarket(PenDomain md, String queryId) {
        try {
            sqlMapClient.insert(queryId, md);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertSegmentMarket(SegmentDomain md, String queryId) {
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

    public List<MarketDomain> getKlineDataOnline(String symbol) {
        List<MarketDomain> list = null;
        try {
            list = sqlMapClient.queryForList("getKlineDataOnline", symbol);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<String> getSymbols() {
        List<String> result = null;
        try {
            result = sqlMapClient.queryForList("getSymbol");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<MarketDomain> getMultiPeriodKlineData(String symbol,String sqlId) {
        List<MarketDomain> list = null;
        try {
            list = sqlMapClient.queryForList(sqlId, symbol);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
