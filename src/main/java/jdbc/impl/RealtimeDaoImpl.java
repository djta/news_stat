package jdbc.impl;

import domain.SymbolsDomain;
import domain.realtime.OpenCloseStatDomain;
import jdbc.Dao.RealtimeDao;
import jdbc.JdbcInital;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 范志伟 on 2018-03-11.
 */
public class RealtimeDaoImpl extends JdbcInital implements RealtimeDao {

    public List<OpenCloseStatDomain> selectDiffValue() {
        List<OpenCloseStatDomain> list = null;
        try {
            list = JdbcInital.sqlMapClient.queryForList("getOpenCloseStat");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }
}
