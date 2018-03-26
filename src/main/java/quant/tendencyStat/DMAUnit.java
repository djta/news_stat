package quant.tendencyStat;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MInteger;
import domain.MarketDomain;
import domain.talib.DMADomain;
import talib.DataFormatTransformUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 范志伟 on 2018-03-26.
 */
public class DMAUnit {
    private static Core core = new Core();
    private int lookback = 0;
    private static MInteger begin = new MInteger();
    private static MInteger length = new MInteger();

    public static void main(String args[]) {
        double[] array = {207.650, 205.160, 210.870, 209.350, 207.250, 209.960, 207.650, 205.160, 188.170, 186.020};
        List<DMADomain> dmaDomainList = dma(array, 2, 3, 5);
        System.out.println(dmaDomainList);
        System.out.println(dmaDomainList.get(0));
    }

    public static List<DMADomain> dma(double[] input, int shortPeriod, int longPeriod, int midPeriod) {
        int inputLength = input.length;
        double[] maShort = new double[inputLength];
        core.sma(0, inputLength - 1, input, shortPeriod, begin, length, maShort);
        double[] maLong = new double[inputLength];
        core.sma(0, inputLength - 1, input, longPeriod, begin, length, maLong);
        double[] dma = new double[inputLength];
        for (int i = 0; i < inputLength; i++) {
            if (maShort[i] == 0.0 || maLong[i] == 0) {
                dma[i] = 0.0;
            } else {
                dma[i] = maShort[i] - maLong[i];
            }
        }
        double[] ama = new double[inputLength];
        core.sma(0, inputLength - 1, dma, midPeriod, begin, length, ama);
        List dmaList = DataFormatTransformUtil.result2List(dma);
        List amaList = DataFormatTransformUtil.result2List(ama);
        List<DMADomain> dmaDomainList = new ArrayList<DMADomain>();
        for (int i = 0; i < dmaList.size(); i++) {
            DMADomain dd = new DMADomain();
            dd.setAma(ama[i]);
            dd.setDma(dma[i]);
            dmaDomainList.add(dd);
        }
        return dmaDomainList;
    }

    public static List<DMADomain> dma(List<MarketDomain> marketDomainList, int shortPeriod, int longPeriod, int midPeriod) {
        double[] input = DataFormatTransformUtil.marketDomainlist2Array(marketDomainList);
        return dma(input, shortPeriod, longPeriod, midPeriod);
    }


}
