package gov.telangana.jobportal.web.domain;

/**
 * Created by deshetti on 4/16/17.
 */
public class CentralJobCount {

    private long count;

    public CentralJobCount(long centralJobCount) {
        this.count = centralJobCount;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

}

