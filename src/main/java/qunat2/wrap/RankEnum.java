package qunat2.wrap;

//级别 1F-5F-30F-day-week-month
public enum RankEnum {
    F1(1), F5(2), F30(3), Day(4), Week(5), Month(6);
    public int value;

    private RankEnum(int value) {
        this.value = value;
    }
}
