package domain;

import java.util.List;

/**
 * Created by hzyuyongmao on 2018/2/26.
 *
 * @Description 交易对
 */
public class CurrencysDomain {
    private String status;
    private List<String> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
