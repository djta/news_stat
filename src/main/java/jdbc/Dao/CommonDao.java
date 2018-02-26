package jdbc.Dao;

import domain.SymbolsDomain;

import java.util.List;

/**
 * Created by hzyuyongmao on 2018/2/26.
 *
 * @Description
 */
public interface CommonDao {
    public void insertSymbols(SymbolsDomain sd);

    public List<SymbolsDomain> selectSymbols();
}
