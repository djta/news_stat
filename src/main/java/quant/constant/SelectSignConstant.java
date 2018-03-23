package quant.constant;

/**
 * Created by hzyuyongmao on 2018/3/23.
 *
 * @Description
 */
public enum SelectSignConstant {
    HIT("hit"), MISS("miss"), HOLD("hold");
    private String sign;

    private SelectSignConstant(String sign) {
        this.sign = sign;
    }

}
