package quant.PatternRecognition;

import domain.MarketDomain;
import quant.PatternRecognition.domain.PRResultDomain;
import quant.constant.TendencySign;
import quant.tendencyStat.TendencyUnit;
import util.DateUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test extends TendencyUnit {
    public static void main(String args[]) {

    }

//    public static List<PRResultDomain> getPatternRecognition(List<MarketDomain> marketDomainList) {
//        int size = marketDomainList.size();
//        double inOpen[] = new double[size];
//        double inHigh[] = new double[size];
//        double inLow[] = new double[size];
//        double inClose[] = new double[size];
//        int[] outInteger = new int[size];
//        List<PRResultDomain> prResultDomains = new ArrayList<PRResultDomain>();
//        for (int i = 0; i < size; i++) {
//            MarketDomain md = marketDomainList.get(i);
//            PRResultDomain prResultDomain = new PRResultDomain();
//            inOpen[i] = md.getOpen();
//            inHigh[i] = md.getHigh();
//            inLow[i] = md.getLow();
//            inClose[i] = md.getClose();
//            prResultDomain.setDateStr(DateUtil.ts2DateStr(String.valueOf(md.getId())));
//            prResultDomain.setSymbol(md.getSymbol());
//            prResultDomains.add(prResultDomain);
//        }
//        core.cdl3LineStrike(0, size - 1, inOpen, inHigh, inLow, inClose, begin, length, outInteger);
//        for (int i = 0; i < outInteger.length; i++) {
//            prResultDomains.get(i).setValue(outInteger[i]);
//        }
//        return prResultDomains;
//    }

    public TendencySign getTendencySign(List<MarketDomain> marketDomainList) {
        return null;
    }


//    public TendencySign getTendencySign(List<MarketDomain> marketDomainList) {
//        List<Integer> resultList = getPatternRecognition(marketDomainList);
//        int size = resultList.size();
//        if (size < 3) {
//            return TendencySign.WAIT;
//        }
//        System.out.println(resultList.get(size - 1));
//        if (resultList.get(size - 1).equals(100) || resultList.get(size - 2).equals(100) || resultList.get(size - 3).equals(100) || resultList.get(size - 4).equals(100)) {
//            System.out.println("Bull");
//            return TendencySign.BULL;
//        } else if (resultList.get(size - 1).equals(-100) || resultList.get(size - 2).equals(-100) || resultList.get(size - 3).equals(-100) || resultList.get(size - 4).equals(-100)) {
//            System.out.println("Bear");
//            return TendencySign.BEAR;
//        } else {
////            System.out.println("Wait");
//            return TendencySign.WAIT;
//        }
//    }
}
