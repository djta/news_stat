package quant.PatternRecognition;

import domain.MarketDomain;
import jdbc.impl.MarketDaoImpl;

import java.util.List;

public class PatternRecognitionContext {
    public static void main(String args[]) {
        System.out.println("ok");
        MarketDaoImpl marketDao = new MarketDaoImpl();
        List<MarketDomain> marketDomains = marketDao.getKlineData("neousdt");
        System.out.println(marketDomains.size());
//        for (int i = 0; i < marketDomains.size() - 250; i++) {
//            List<MarketDomain> list = marketDomains.subList(i, i + 250);
//
//        }

        List<Integer> recognitionResults = Test.getPatternRecognition(marketDomains);
        for (Integer value : recognitionResults) {
            System.out.print(value);
        }

    }
}
