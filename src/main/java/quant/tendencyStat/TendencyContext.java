package quant.tendencyStat;

import domain.MarketDomain;
import domain.talib.DMADomain;
import domain.talib.MacdDomain;
import quant.constant.TendencySign;
import quant.constant.TradeSign;

import java.util.List;

/**
 * Created by 范志伟 on 2018-03-26.
 */
public class TendencyContext {

    private double bearRange;
    private double bullRange;
    private List<TendencyUnit> tendencyUnits;

    public TendencyContext(double bearRange, double bullRange, List<TendencyUnit> tendencyUnits) {
        this.bearRange = bearRange;
        this.bullRange = bullRange;
        this.tendencyUnits = tendencyUnits;
    }

    public static void main(String args[]) {

    }

    public TradeSign getTradeSign(List<MarketDomain> marketDomains) {
        int count = 0;
        for (TendencyUnit tu : tendencyUnits) {
            count += tu.getTendencySign(marketDomains).value;
        }
//        System.out.println("TendencySize:" + count);
        int size = tendencyUnits.size();
        if (count >= size * bullRange) {
            return TradeSign.OPEN;
        } else if (count <= -size * bearRange) {
            return TradeSign.CLOSE;
        } else {
            return TradeSign.WAIT;
        }

    }

    public TradeSign getTradeSignMultiPeriod(List<MarketDomain> marketDomains) {
        int count = 0;
        for (TendencyUnit tu : tendencyUnits) {
            count += tu.getTendencySignMultiPeriod(marketDomains).value;
        }
        int size = tendencyUnits.size();
        if (count >= size * bullRange) {
            return TradeSign.OPEN;
        }
        if (count <= -size * bearRange) {
            return TradeSign.CLOSE;
        }
        return TradeSign.WAIT;

    }

}
