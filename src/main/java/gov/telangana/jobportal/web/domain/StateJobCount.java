package gov.telangana.jobportal.web.domain;

/**
 * Created by deshetti on 4/16/17.
 */
public class StateJobCount {

    private long count;

    public StateJobCount(long stateJobCount) {
        this.count = stateJobCount;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

}
