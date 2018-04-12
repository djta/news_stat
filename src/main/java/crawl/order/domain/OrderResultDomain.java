package crawl.order.domain;

/**
 * Created by 范志伟 on 2018-04-13.
 */
public class OrderResultDomain {
    private String status;
    private String data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "OrderResultDomain{" +
                "status='" + status + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
