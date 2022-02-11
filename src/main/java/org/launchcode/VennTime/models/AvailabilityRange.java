package org.launchcode.VennTime.models;

import javax.persistence.Embeddable;
import java.sql.Timestamp;

@Embeddable
public class AvailabilityRange {

    public AvailabilityRange() {
    }

    public AvailabilityRange(Timestamp startTime, Timestamp endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    private Timestamp startTime;

    private Timestamp endTime;

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
}
