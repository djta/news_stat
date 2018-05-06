package quant.PatternRecognition;

public enum PRSign {
    UP1(100), DOWN1(-100), UP2(-100), DOWN2(100);
    public int value;

    private PRSign(int value) {
        this.value = value;
    }
}
