package quant.PatternRecognition;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MInteger;
import domain.MarketDomain;
import quant.constant.TendencySign;

import java.util.List;

public abstract class PatternRecognitionUnit {
    public static Core core = new Core();
    int lookback = 0;
    static MInteger begin = new MInteger();
    static MInteger length = new MInteger();

    public abstract TendencySign getTendencySign(List<MarketDomain> marketDomainList);

    public void test(){


    }
}
