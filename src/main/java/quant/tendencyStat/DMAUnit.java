package quant.tendencyStat;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MInteger;
import domain.MarketDomain;
import domain.talib.DMADomain;
import quant.constant.TendencySign;
import talib.DataFormatTransformUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 范志伟 on 2018-03-26.
 */
public class DMAUnit extends TendencyUnit {
    private int shortPeriod;
    private int longPeriod;
    private int midPeriod;

    public DMAUnit(int shortPeriod, int longPeriod, int midPeriod) {
        this.shortPeriod = shortPeriod;
        this.longPeriod = longPeriod;
        this.midPeriod = midPeriod;
    }

    public static void main(String args[]) {
        double[] array = {207.650, 205.160, 210.870, 209.350, 207.250, 209.960, 207.650, 205.160, 188.170, 186.020};
        List<DMADomain> dmaDomainList = dma(array, 2, 3, 2);
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
        List<Double> dmaList = DataFormatTransformUtil.result2List(dma);
        List<Double> amaList = DataFormatTransformUtil.result2List(ama);
        List<DMADomain> dmaDomainList = new ArrayList<DMADomain>();
        for (int i = 0; i < dmaList.size(); i++) {
            DMADomain dd = new DMADomain();
            dd.setAma(amaList.get(i));
            dd.setDma(dmaList.get(i));
            dmaDomainList.add(dd);
        }
        return dmaDomainList;
    }

    public static List<DMADomain> dma(List<MarketDomain> marketDomainList, int shortPeriod, int longPeriod, int midPeriod) {
        double[] input = DataFormatTransformUtil.marketDomainlist2Array(marketDomainList);
        return dma(input, shortPeriod, longPeriod, midPeriod);
    }


    public TendencySign getTendencySign(List<MarketDomain> marketDomainList) {
        List<DMADomain> dmaDomainList = DMAUnit.dma(marketDomainList, shortPeriod, longPeriod, midPeriod);
        int size = dmaDomainList.size();
        if (size < 2) {
            return TendencySign.WAIT;
        }
        DMADomain dd1 = dmaDomainList.get(size - 1);
        DMADomain dd2 = dmaDomainList.get(size - 2);
//        if (dd1.getDma() > dd2.getAma() && dd1.getDma() > dd1.getAma() && dd2.getDma() < dd2.getAma()) {
//            return TendencySign.BULL;
//        }
//        if (dd1.getDma() < dd2.getAma() && dd1.getDma() < dd1.getAma() && dd2.getDma() > dd2.getAma()) {
//            return TendencySign.BEAR;
//        }
        if (dd1.getAma() > 0 && dd1.getDma() > 0 && dd1.getDma() > dd2.getDma() && dd1.getAma() > dd2.getAma()) {
            return TendencySign.BULL;
        }
        if (dd1.getAma() < 0 && dd1.getDma() < 0 && dd1.getDma() < dd2.getDma() && dd1.getAma() < dd2.getAma()) {
            return TendencySign.BEAR;
        }


        return TendencySign.WAIT;
    }

    public static void printArray(double[] input) {
        for (double value : input) {
            System.out.print(value + "\t");
        }
        System.out.println();
    }

}
