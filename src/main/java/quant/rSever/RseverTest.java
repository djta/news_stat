package quant.rSever;

import org.rosuda.REngine.Rserve.RConnection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RseverTest {
    public static void main(String args[]) {
        RConnection connection = null;
        try {
            connection = new RConnection();
            String vector = "c(12,18,23,25,36,42,67,74,80,90,114,135,144,176)";
            connection.eval("summaryVal<-summary(" + vector + ")");
            double[] mean = connection.eval("summaryVal").asDoubles();
            for (double val : mean) {
                System.out.println(val);
            }
            double[] doubleTest = {12, 18, 23, 25, 36, 42, 67, 74, 80, 90, 114, 135, 144, 176};

            connection.assign("test", doubleTest);
            double sum = connection.eval("sum(test)").asDouble();
            System.out.println(sum);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
