package gov.telangana.jobportal.web.domain;

/**
 * Created by deshetti on 4/16/17.
 */
public class OtherJobCount {

    private long count;

    public OtherJobCount(long otherJobCount) {
        this.count = otherJobCount;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
