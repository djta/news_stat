package quant.SelectSymbolUnit;

import domain.MarketDomain;
import quant.constant.SelectSignConstant;

import java.util.Map;

/**
 * Created by hzyuyongmao on 2018/3/23.
 *
 * @Description 开口向下或向上选取模块
 */
public class OpenJumpUnit extends SelectSymbolUnit {
    public SelectSignConstant getSelectSymbol(Map<Long, MarketDomain> klineMap) {
        return null;
    }
}
