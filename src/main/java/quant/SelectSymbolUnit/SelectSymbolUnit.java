package quant.SelectSymbolUnit;

import domain.MarketDomain;
import indicator.unit.GappedOpenUnit;
import javafx.print.Collation;
import quant.TimeAdjust;
import quant.constant.SelectSignConstant;

import java.util.*;

/**
 * Created by hzyuyongmao on 2018/3/23.
 *
 * @Description
 */
public abstract class SelectSymbolUnit {
    public abstract SelectSignConstant getSelectSymbol(Map<Long, MarketDomain> klineMap);

    public static void main(String args[]) {
        TreeMap<Long, MarketDomain> treeMap = new TreeMap<Long, MarketDomain>();
        MarketDomain md1 = new MarketDomain();
        md1.setOpen(5);
        md1.setClose(3);
        md1.setHigh(8);
        md1.setLow(2);
        treeMap.put(1521804580L, md1);
        MarketDomain md2 = new MarketDomain();
        md2.setOpen(9);
        md2.setClose(5);
        md2.setHigh(11);
        md2.setLow(6);
        treeMap.put(1521803580L, md2);

        Long lastTime = treeMap.lastKey();
        System.out.println(lastTime);
        if (!TimeAdjust.getMinDiff(lastTime, 500)) {
            System.out.println("wait time out");
            return;
        }
        List<MarketDomain> resultList = new ArrayList<MarketDomain>();
        Iterator it = treeMap.keySet().iterator();
        int i = 0;
        while (it.hasNext() && i < 5) {
            i++;
            Long key = (Long) it.next();
            MarketDomain value = treeMap.get(key);
            resultList.add(value);
            System.out.println(value);
        }
        System.out.println(resultList);
        Collections.reverse(resultList);
        List<String> ft = GappedOpenUnit.getGappedOpenUpDown(resultList);
        System.out.println(ft);

    }
}
