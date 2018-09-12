package qunat2.sign;

//形态
public enum FormSign {
    TREND_UP(1), ADJUST(0), TREND_DOWN(-1);
    public int value;

    private FormSign(int value) {
        this.value = value;
    }
}
