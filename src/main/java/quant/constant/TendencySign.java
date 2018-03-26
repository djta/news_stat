package quant.constant;

/**
 * Created by hzyuyongmao on 2018/3/26.
 *
 * @Description
 */
public enum TendencySign {
    BULL(1), WAIT(0), BEAR(-1);
    public int value;

    private TendencySign(int value) {
        this.value = value;
    }
}
