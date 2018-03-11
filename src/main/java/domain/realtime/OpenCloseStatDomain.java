package domain.realtime;

/**
 * Created by 范志伟 on 2018-03-11.
 */
public class OpenCloseStatDomain {
    private String symbol;
    private double min_index;
    private double max_index;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getMin_index() {
        return min_index;
    }

    public void setMin_index(double min_index) {
        this.min_index = min_index;
    }

    public double getMax_index() {
        return max_index;
    }

    public void setMax_index(double max_index) {
        this.max_index = max_index;
    }

    @Override
    public String toString() {
        return "OpenCloseStatDomain{" +
                "symbol='" + symbol + '\'' +
                ", min_index=" + min_index +
                ", max_index=" + max_index +
                '}';
    }
}
