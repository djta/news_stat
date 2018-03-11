package jdbc.Dao;

import domain.realtime.OpenCloseStatDomain;

import java.util.List;

/**
 * Created by 范志伟 on 2018-03-11.
 */
public interface RealtimeDao {
    public List<OpenCloseStatDomain> selectDiffValue();
}
