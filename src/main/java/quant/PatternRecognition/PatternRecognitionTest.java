package quant.PatternRecognition;

import domain.MarketDomain;
import jdbc.impl.MarketDaoImpl;
import quant.PatternRecognition.domain.PRResultDomain;

import java.util.List;

public class PatternRecognitionTest {
    public static void main(String args[]) {
        MarketDaoImpl marketDao = new MarketDaoImpl();
        List<String> symobls = marketDao.getSymbols();
        for (String symbol : symobls) {
            List<MarketDomain> marketDomains = marketDao.getKlineDataOnline(symbol);
            System.out.println("input size:" + marketDomains.size());
            List<PRResultDomain> results = PatternRecognitionUnit.patternRecognition(marketDomains);
            System.out.println("results size:" + results.size());
//            for (PRResultDomain prResultDomain : results) {
//                if (prResultDomain.getCDLTRISTAR() != null) {
//                    System.out.println(prResultDomain);
//                }
//            }
            for (int i = 0; i < marketDomains.size() - 50; i++) {
                PRTest.getSign(marketDomains.subList(i, i + 50));
            }


        }
    }
}
