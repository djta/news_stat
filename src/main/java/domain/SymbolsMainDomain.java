package domain;

import java.util.List;

/**
 * Created by hzyuyongmao on 2018/2/26.
 *
 * @Description
 */
public class SymbolsMainDomain {
    private String status;
    private List<SymbolsDomain> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SymbolsDomain> getData() {
        return data;
    }

    public void setData(List<SymbolsDomain> data) {
        this.data = data;
    }
}
