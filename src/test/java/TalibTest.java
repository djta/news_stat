import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MInteger;

/**
 * Created by hzyuyongmao on 2018/4/18.
 *
 * @Description
 */
public class TalibTest {
    public static Core core = new Core();
    int lookback = 0;
    public static MInteger begin = new MInteger();
    public static MInteger length = new MInteger();

    public static void main(String args[]) {
        double input[] = {1, 2, 3, 4, 5, 6, 7};
        double output[] = new double[input.length];
        core.stdDev(0, input.length - 1, input, 2, 1, begin, length, output);
        for(double value:output){
            System.out.print(value+"\t");
        }
    }
}
