package quant.PatternRecognition;

import domain.MarketDomain;

import java.util.List;

public class PRTest {
    public static void getSign(List<MarketDomain> marketDomainList) {
//        STAR:REF(CLOSE,2)/REF(OPEN,2)<0.95&&
//                REF(OPEN,1)<REF(CLOSE,2)&&
//                ABS(REF(OPEN,1)-REF(CLOSE,1))/REF(CLOSE,1)<0.03&&
//                CLOSE/OPEN>1.05&&CLOSE>REF(CLOSE,2);
        int size = marketDomainList.size();
        double close2 = marketDomainList.get(size - 3).getClose();
        double open2 = marketDomainList.get(size - 3).getOpen();
        double close1 = marketDomainList.get(size - 2).getClose();
        double open1 = marketDomainList.get(size - 2).getOpen();
        double close = marketDomainList.get(size - 1).getClose();
        double open = marketDomainList.get(size - 1).getOpen();
        System.out.println("close2 / open2:" + close2 / open2);
        System.out.println("Math.abs(open1 - close1)):" + Math.abs(open1 - close1));
        System.out.println("close / open:" + close / open);
        if ((close2 / open2) < 0.95 && open1 < close2 && ((Math.abs(open1 - close1)) / close1) < 0.03 && (close / open) > 1.05 && close > close2) {
            System.out.println(marketDomainList.get(size - 1));
        }

    }
}
