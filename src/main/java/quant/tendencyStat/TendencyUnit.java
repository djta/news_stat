package quant.tendencyStat;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MInteger;
import domain.MarketDomain;
import quant.constant.TendencySign;

import java.util.List;

/**
 * Created by hzyuyongmao on 2018/4/2.
 *
 * @Description
 */
public abstract class TendencyUnit {
    public static Core core = new Core();
    int lookback = 0;
    public static MInteger begin = new MInteger();
    public static MInteger length = new MInteger();

    public abstract TendencySign getTendencySign(List<MarketDomain> marketDomainList);

    public TendencySign getTendencySignMultiPeriod(List<MarketDomain> marketDomainList) {
        return TendencySign.WAIT;
    }

}
