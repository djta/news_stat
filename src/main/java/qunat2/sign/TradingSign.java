package qunat2.sign;

//
public enum TradingSign {
    ADD(1), WAIT(0), SUBTRACT(-1);
    public int value;

    private TradingSign(int value) {
        this.value = value;
    }
}
