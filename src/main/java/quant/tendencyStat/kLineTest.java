package quant.tendencyStat;

import com.tictactec.ta.lib.MInteger;
import domain.MarketDomain;
import quant.constant.TendencySign;
import talib.DataFormatTransformUtil;

import java.util.List;

/**
 * Created by hzyuyongmao on 2018/4/9.
 *
 * @Description
 */
public class kLineTest extends TendencyUnit {

    public TendencySign getTendencySign(List<MarketDomain> marketDomainList) {
        return null;
    }

    public static void main(String args[]) {
        double[] array = {207.650, 205.160, 210.870, 209.350, 207.250, 209.960, 207.650, 205.160, 188.170, 186.020};
        double[] output = new double[array.length];
        //斜率
        core.linearRegSlope(0, array.length - 1, array, 5, begin, length, output);
        List<Double> list = DataFormatTransformUtil.result2List(output);
        System.out.println(list);


    }
}
