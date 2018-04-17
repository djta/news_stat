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
          List<MarketDomain> marketDomains1= marketDomains.subList(0,1185);
        List<Integer> recognitionResults = Test.getPatternRecognition(marketDomains1);
//        for (Integer value : recognitionResults) {
//            System.out.println(value);
//        }
        for(int i=0;i<recognitionResults.size();i++){
            if(recognitionResults.get(i)==100){
                System.out.println(recognitionResults.get(i));
                System.out.println(i);
                break;
            }
        }

    }
}
