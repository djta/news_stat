package crawl.account.domain;

/**
 * Created by 范志伟 on 2018-04-12.
 */
public class AccountsBalanceDomain {
    private String status;
    private AccountsData data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AccountsData getData() {
        return data;
    }

    public void setData(AccountsData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AccountsBalanceDomain{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
