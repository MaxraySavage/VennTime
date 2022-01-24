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
}
