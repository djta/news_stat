package quant.PatternRecognition;

import domain.MarketDomain;
import quant.constant.TendencySign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test extends PatternRecognitionUnit {
    public static void main(String args[]) {

    }

    public static List getPatternRecognition(List<MarketDomain> marketDomainList) {
        int size = marketDomainList.size();
        double inOpen[] = new double[size];
        double inHigh[] = new double[size];
        double inLow[] = new double[size];
        double inClose[] = new double[size];
        int[] outInteger = new int[size];
        for (int i = 0; i < size; i++) {
            MarketDomain md = marketDomainList.get(i);
            inOpen[i] = md.getOpen();
            inHigh[i] = md.getHigh();
            inLow[i] = md.getLow();
            inClose[i] = md.getClose();
        }
        core.cdl3Inside(0, size - 1, inOpen, inHigh, inLow, inClose, begin, length, outInteger);
        List<Integer> resultList = new ArrayList<Integer>();
        for (int i = 0; i < outInteger.length; i++) {
            resultList.add(outInteger[i]);
        }
        return resultList;
    }


    public TendencySign getTendencySign(List<MarketDomain> marketDomainList) {

        return null;
    }
}
