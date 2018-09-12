package qunat2.wrap;

public enum PartEnum {
    TOP(1), LEVEL(0), BOTTOM(-1);
    public int value;

    private PartEnum(int value) {
        this.value = value;
    }
}
