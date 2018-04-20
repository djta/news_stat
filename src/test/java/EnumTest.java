import quant.constant.TendencySign;

/**
 * Created by hzyuyongmao on 2018/3/26.
 *
 * @Description
 */
public class EnumTest {
    public static void main(String args[]) {
        String d = TendencySign.BEAR.name();
        System.out.println(d);
        int value = TendencySign.BULL.value + TendencySign.WAIT.value;
        System.out.println(value);
    }
}




