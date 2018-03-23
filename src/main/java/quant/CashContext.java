package quant;

/**
 * Created by hzyuyongmao on 2018/3/23.
 *
 * @Description
 */
public class CashContext {
    private double amount; //总量
    private double rate; //费率
    private double slip;//滑点影响

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getSlip() {
        return slip;
    }

    public void setSlip(double slip) {
        this.slip = slip;
    }

    @Override
    public String toString() {
        return "CashContext{" +
                "amount=" + amount +
                ", rate=" + rate +
                ", slip=" + slip +
                '}';
    }
}
