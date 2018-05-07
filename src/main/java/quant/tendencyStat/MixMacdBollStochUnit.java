package quant.tendencyStat;

import domain.MarketDomain;
import quant.constant.TendencySign;

import java.util.List;

/**
 * Created by hzyuyongmao on 2018/5/7.
 *https://blog.csdn.net/qq445803843/article/details/45179493
 * macd,boll,stoch融合
 * @Description
 */
public class MixMacdBollStochUnit extends TendencyUnit {
    private int macdShortPeriod;
    private int macdLongPeriod;
    private int macdMidPeriod;
    private int fastKPeriod;
    private int slowKPeriod;
    private int slowDPeriod;
    private int bollPeriod;
    public MixMacdBollStochUnit(int macdShortPeriod, int macdLongPeriod, int macdMidPeriod,
                                int fastKPeriod, int slowKPeriod, int slowDPeriod,int bollPeriod) {
        this.macdShortPeriod = macdShortPeriod;
        this.macdLongPeriod = macdLongPeriod;
        this.macdMidPeriod = macdMidPeriod;
        this.fastKPeriod = fastKPeriod;
        this.slowKPeriod = slowKPeriod;
        this.slowDPeriod = slowDPeriod;
        this.bollPeriod=bollPeriod;
    }


    public TendencySign getTendencySign(List<MarketDomain> marketDomainList) {




        return null;
    }
}
