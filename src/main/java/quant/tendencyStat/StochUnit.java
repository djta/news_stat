package quant.tendencyStat;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MAType;
import com.tictactec.ta.lib.MInteger;
import domain.MarketDomain;
import domain.talib.StochDomain;
import quant.constant.TendencySign;
import talib.DataFormatTransformUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 范志伟 on 2018-04-01.
 */
public class StochUnit extends TendencyUnit {
    private static Core core = new Core();
    private int lookback = 0;
    private static MInteger begin = new MInteger();
    private static MInteger length = new MInteger();
    private static MAType mtK = MAType.Dema;
    private static MAType mtD = MAType.Dema;


    private int fastKPeriod;
    private int slowKPeriod;
    private int slowDPeriod;

    public StochUnit(int fastKPeriod, int slowKPeriod, int slowDPeriod) {
        this.fastKPeriod = fastKPeriod;
        this.slowKPeriod = slowKPeriod;
        this.slowDPeriod = slowDPeriod;
    }


    public static void main(String args[]) {
        double[] high = {30, 120, 240, 2109.350, 2071.250, 3209.960, 207.6350, 2025.160, 1818.170, 1686.020};
        double[] low = {10, 10, 1230, 2209.350, 20371.250, 2049.9460, 2107.650, 2065.160, 1838.170, 1826.020};
        double[] close = {20, 20, 130, 2029.350, 2037.250, 2509.960, 207.650, 2405.160, 1828.170, 1586.020};
        double[] outputD = new double[high.length];
        double[] outputk = new double[high.length];
        core.stoch(0, high.length - 1, high, low, close, 5, 15, mtK, 25, mtD, begin, length, outputk, outputD);
        for (int i = 0; i < high.length; i++) {
            System.out.print(outputD[i] + "\t");
        }
        System.out.println();
        for (int i = 0; i < high.length; i++) {
            System.out.print(outputk[i] + "\t");
        }

    }

    public static List<StochDomain> getStochValue(double[] inputHigh, double[] inputLow, double[] inputClose, int fastKPeriod, int slowKPeriod, int slowDPeriod) {
        double[] outputD = new double[inputHigh.length];
        double[] outputk = new double[inputHigh.length];
        core.stoch(0, inputHigh.length - 1, inputHigh, inputLow, inputClose, 5, 15, mtK, 25, mtD, begin, length, outputk, outputD);
        List<Double> outputKlist = DataFormatTransformUtil.result2List(outputk);
        List<Double> outputDlist = DataFormatTransformUtil.result2List(outputD);
        List<StochDomain> stochDomains = new ArrayList<StochDomain>();
        for (int i = 0; i < outputDlist.size(); i++) {
            StochDomain stochDomain = new StochDomain();
            stochDomain.setSlowD(outputDlist.get(i));
            stochDomain.setSlowK(outputKlist.get(i));
            stochDomains.add(stochDomain);
        }
        return stochDomains;

    }


    public static List<StochDomain> getStochValue(int fastKPeriod, int slowKPeriod, int slowDPeriod, List<MarketDomain> marketDomainList) {
        int size = marketDomainList.size();
        double[] inputHigh = new double[size];
        double[] inputLow = new double[size];
        double[] inputClose = new double[size];
        for (int i = 0; i < size; i++) {
            inputHigh[i] = marketDomainList.get(i).getHigh();
            inputLow[i] = marketDomainList.get(i).getLow();
            inputClose[i] = marketDomainList.get(i).getClose();
        }
        List<StochDomain> stochDomains = getStochValue(inputHigh, inputLow, inputClose, fastKPeriod, slowKPeriod, slowDPeriod);
        return stochDomains;
    }


    public TendencySign getTendencySign(List<MarketDomain> marketDomainList) {
        int size = marketDomainList.size();
        double[] inputHigh = new double[size];
        double[] inputLow = new double[size];
        double[] inputClose = new double[size];
        for (int i = 0; i < size; i++) {
            inputHigh[i] = marketDomainList.get(i).getHigh();
            inputLow[i] = marketDomainList.get(i).getLow();
            inputClose[i] = marketDomainList.get(i).getClose();
        }
        List<StochDomain> stochDomains = getStochValue(inputHigh, inputLow, inputClose, fastKPeriod, slowKPeriod, slowDPeriod);
        if (stochDomains.size() == 0) {
            return TendencySign.WAIT;
        }
        StochDomain stochDomain1 = stochDomains.get(stochDomains.size() - 1);
        StochDomain stochDomain2 = stochDomains.get(stochDomains.size() - 2);
        if (stochDomain1.getSlowD() >= 90 && stochDomain1.getSlowK() >= 90
                && stochDomain2.getSlowK() < stochDomain2.getSlowD()
                && stochDomain1.getSlowK() > stochDomain1.getSlowD()) {
            return TendencySign.BEAR;
        }
        if (stochDomain1.getSlowD() < 20 && stochDomain1.getSlowK() < 20) {
            return TendencySign.BULL;
        }

//        if (stochDomain1.getSlowD() > 80 && stochDomain1.getSlowK() > 80
////                && stochDomain2.getSlowK() > stochDomain2.getSlowD()
////                && stochDomain1.getSlowK() < stochDomain1.getSlowD()
//                ) {
//            return TendencySign.BEAR;
//        }
//        if (stochDomain1.getSlowD() < 20 && stochDomain1.getSlowK() < 20
//                && stochDomain2.getSlowK() < stochDomain2.getSlowD()
//                && stochDomain1.getSlowK() > stochDomain1.getSlowD()) {
//            return TendencySign.BULL;
//        }

        return TendencySign.WAIT;
    }
}
