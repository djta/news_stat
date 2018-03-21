package indicator.tabLibUnit;

/**
 * Created by 范志伟 on 2018-03-21.
 */

import com.tictactec.ta.lib.*;

public class TabLibTest {
    double[] array = {207.650, 205.160, 210.870, 209.350, 207.250, 209.960, 207.650, 205.160, 188.170, 186.020, 207.650, 205.160, 188.170, 186.020, 207.650, 205.160, 188.170, 186.020};
    double[] output = new double[array.length];
    int period = 3;
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
    public  void callEMA(){
//        lookback=core.emaLookback(period);

        System.out.println(begin.value);
        core.ema(0, array.length - 1, array, 3, begin, length, output);
        System.out.println("TEMA Output: ");
        print();
    }

    public void print() {
        for(int i=0;i<array.length;i++) {
            System.out.print(output[i] + "\t ");
        }
        System.out.println("");
    }

    public static void main(String args[]) {
        TabLibTest obj = new TabLibTest();
        obj.callDEMA();
        obj.callTEMA();
        obj.callEMA();
    }


}
