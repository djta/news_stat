package crawl.account.domain;

/**
 * Created by 范志伟 on 2018-04-12.
 */
public class BalanceSymbolDetail {
    private String currency;//币种
    private String type;//类型，trade:交易余额，frozen冻结余额
    private String balance;//余额

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BalanceSymbolDetail{" +
                "currency='" + currency + '\'' +
                ", type='" + type + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}
