package quant.constant;

/**
 * Created by hzyuyongmao on 2018/4/2.
 *
 * @Description
 */
public enum TradeSign {
    OPEN(1), WAIT(0), CLOSE(-1);
    public int value;

    private TradeSign(int value) {
        this.value = value;
    }
}
