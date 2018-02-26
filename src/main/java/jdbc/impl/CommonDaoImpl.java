package jdbc.impl;

import domain.SymbolsDomain;
import jdbc.Dao.CommonDao;
import jdbc.JdbcInital;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by hzyuyongmao on 2018/2/26.
 *
 * @Description
 */
public class CommonDaoImpl extends JdbcInital implements CommonDao {
    public void insertSymbols(SymbolsDomain sd) {
        try {
            JdbcInital.sqlMapClient.insert("insertSymbols", sd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<SymbolsDomain> selectSymbols() {
        List<SymbolsDomain> list = null;
        try {
            list = JdbcInital.sqlMapClient.queryForList("getSymbols");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
