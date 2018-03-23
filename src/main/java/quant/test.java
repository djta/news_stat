package quant;

import domain.MarketDomain;
import quant.SelectSymbolUnit.OpenJumpUnit;
import quant.SelectSymbolUnit.SelectSymbolContext;
import quant.SelectSymbolUnit.SelectSymbolUnit;
import quant.constant.SelectSignConstant;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by hzyuyongmao on 2018/3/23.
 *
 * @Description
 */
public class test {
    public static void main(String args[]) {
        System.out.println(SelectSignConstant.HIT);
        SelectSymbolUnit ssu = new OpenJumpUnit();
        Map<String, Map<Long, MarketDomain>> map = new HashMap<String, Map<Long, MarketDomain>>();

    }
}
