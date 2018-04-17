package quant.tendencyStat;

import com.sun.xml.internal.ws.util.Pool;
import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MInteger;
import domain.MarketDomain;
import quant.constant.TendencySign;
import talib.DataFormatTransformUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzyuyongmao on 2018/3/26.
 *
 * @Description
 */
public class TrixUnit extends TendencyUnit {
  private int period;
  private int maPeriod;

    public TrixUnit(int period, int maPeriod) {
        this.period = period;
        this.maPeriod = maPeriod;
    }

    public static void main(String args[]) {
//        double[] array = {207.650, 205.160, 210.870, 209.350, 207.250, 209.960, 207.650, 205.160, 188.170, 186.020, 111, 232, 111, 122};
        double[] array = {201.650, 207.650, 207.650, 207.650, 207.610, 207.650, 207.650, 207.630, 207.650, 207.650};
        List list = trix(array, 2);
        System.out.println(list);

    }

    public static List trix(double[] inputData, int period) {
        int inputLength = inputData.length;
        double[] outputData = new double[inputLength];
        if (period <= 1) {
            return new ArrayList();
        }
        core.trix(0, inputLength - 1, inputData, period, begin, length, outputData);
        List list = DataFormatTransformUtil.result2List(outputData);
        return list;

    }

    public static List trix(List<MarketDomain> inputDomain, int period) {
        double[] inputData = DataFormatTransformUtil.marketDomainlist2Array(inputDomain);
        return trix(inputData, period);

    }

    public static List mTrix(double[] inputData, int period, int maPeriod) {
        int inputLength = inputData.length;
        double[] outputData = new double[inputLength];
        if (period <= 1) {
            return new ArrayList();
        }
        core.trix(0, inputLength - 1, inputData, period, begin, length, outputData);
        double[] mOutputData = new double[inputLength];
        core.sma(0, inputLength - 1, outputData, maPeriod, begin, length, mOutputData);
        List list = DataFormatTransformUtil.result2List(mOutputData);
        return list;
    }

    public static List mTrix(List<MarketDomain> marketDomainList, int period, int maPeriod) {
        double[] inputData = DataFormatTransformUtil.marketDomainlist2Array(marketDomainList);
        return mTrix(inputData, period, maPeriod);
    }
    public  TendencySign getTendencySign(List<MarketDomain> marketDomainList) {
        List<Double> trix = TrixUnit.trix(marketDomainList, period);
        List<Double> mtrix = TrixUnit.mTrix(marketDomainList, period, maPeriod);
        int trixSize = trix.size();
        int mtrixSize = mtrix.size();
        if (trixSize < 2 || mtrixSize < 2) {
            return TendencySign.WAIT;
        }
        if (trix.get(trixSize - 1) > trix.get(trixSize - 2) && trix.get(trixSize - 1) > mtrix.get(mtrixSize - 1) && trix.get(trixSize - 2) < mtrix.get(mtrixSize - 2)) {
            return TendencySign.BULL;
        }
        if (trix.get(trixSize - 1) < trix.get(trixSize - 2) && trix.get(trixSize - 1) < mtrix.get(mtrixSize - 1) && trix.get(trixSize - 2) > mtrix.get(mtrixSize - 2)) {
            return TendencySign.BEAR;
        }
        return TendencySign.WAIT;

    }



}
