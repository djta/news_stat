package crawl.account.domain;

import java.util.List;

/**
 * Created by 范志伟 on 2018-04-12.
 */
public class AccountsData {
    private long id;
    private String type;//账户类型：spot现货账户
    private String state;//账户状态，working：正常，lock:账户被锁
    private List<BalanceSymbolDetail> list;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<BalanceSymbolDetail> getList() {
        return list;
    }

    public void setList(List<BalanceSymbolDetail> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "AccountsData{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", state='" + state + '\'' +
                ", list=" + list +
                '}';
    }
}
