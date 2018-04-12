package crawl.common;

/**
 * Created by hzyuyongmao on 2018/4/12.
 *
 * @Description
 */
public class CommonTimestampDomain {
    private String status;
    private long data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    public CommonTimestampDomain(String status, long data) {
        this.status = status;
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommonTimestampDomain{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
