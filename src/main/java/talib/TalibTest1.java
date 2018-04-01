package talib;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MInteger;
import quant.tendencyStat.MaUnit;

import java.util.List;

/**
 * Created by hzyuyongmao on 2018/3/26.
 * http://mrjbq7.github.io/ta-lib/doc_index.html
 *
 * @Description
 */
public class TalibTest1 {
    static Core core = new Core();
    int lookback = 0;
    static MInteger begin = new MInteger();
    static MInteger length = new MInteger();

    public static void main(String args[]) {
        double[] array = {0, 0, 0, 209.350, 207.250, 209.960, 207.650, 205.160, 188.170, 186.020};
        List list = MaUnit.sma(array, 2);
        System.out.println(list);
        double[] output = new double[array.length];
        core.ema(0, array.length - 1, array, 3, begin, length, output);
        for (int i = 0; i < array.length; i++) {
            System.out.print(output[i] + "\t");
        }

    }


}
