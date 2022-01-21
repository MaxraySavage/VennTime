package org.launchcode.VennTime.models;

import javax.persistence.Embeddable;
import java.sql.Timestamp;

@Embeddable
public class AvailabilityRange {

    private Timestamp startTime;

    private Timestamp endTime;
}
