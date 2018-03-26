package talib;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MInteger;

/**
 * Created by hzyuyongmao on 2018/3/21.
 *
 * @Description
 */
public class TaLibDemo {
    double[] array = {207.650, 205.160, 210.870, 209.350, 207.250, 209.960, 207.650, 205.160, 188.170, 186.020};
    double[] output = new double[array.length];
    int period = 5;
    Core core = new Core();
    int lookback = 0;
    MInteger begin = new MInteger();
    MInteger length = new MInteger();

    public void callDEMA() {
        lookback = core.demaLookback(period);
        core.dema(0, array.length - 1, array, 3, begin, length, output);
        System.out.println("DEMA Output: ");
        print();
    }

    public void callTEMA() {
        lookback = core.temaLookback(period);
        core.tema(0, array.length - 1, array, 3, begin, length, output);
        System.out.println("TEMA Output: ");
        print();
    }

    public void callSMA() {
        lookback = core.temaLookback(period);
        core.sma(0, array.length - 1, array, 2, begin, length, output);
        System.out.println("SMA Output: ");
        print();
    }

    public void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(output[i] + "\t ");
        }
        System.out.println("");
    }

    public static void main(String args[]) {
        TaLibDemo obj = new TaLibDemo();
        obj.callDEMA();
        obj.callTEMA();
        obj.callSMA();
    }

}
