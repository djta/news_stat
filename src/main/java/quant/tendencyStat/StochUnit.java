package quant.tendencyStat;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MAType;
import com.tictactec.ta.lib.MInteger;

/**
 * Created by 范志伟 on 2018-04-01.
 */
public class StochUnit {
    private static Core core = new Core();
    private int lookback = 0;
    private static MInteger begin = new MInteger();
    private static MInteger length = new MInteger();
    private static MAType mtK = MAType.Dema;
    private static MAType mtD = MAType.Dema;

    public static void main(String args[]) {
        double[] high = {30, 120, 240, 2109.350, 2071.250, 3209.960, 207.6350, 2025.160, 1818.170, 1686.020};
        double[] low = {10, 10, 1230, 2209.350, 20371.250, 2049.9460, 2107.650, 2065.160, 1838.170, 1826.020};
        double[] close = {20, 20, 130, 2029.350, 2037.250, 2509.960, 207.650, 2405.160, 1828.170, 1586.020};
        double[] outputD = new double[high.length];
        double[] outputk = new double[high.length];
        core.stoch(0, high.length - 1, high, low, close, 5, 15, mtK, 25, mtD, begin, length, outputD, outputk);
        for (int i = 0; i < high.length; i++) {
            System.out.print(outputD[i] + "\t");
        }
        System.out.println();
        for (int i = 0; i < high.length; i++) {
            System.out.print(outputk[i] + "\t");
        }
        
    }
}
