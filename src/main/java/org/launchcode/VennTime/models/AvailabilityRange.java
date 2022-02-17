package org.launchcode.VennTime.models;

import javax.persistence.Embeddable;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.List;

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

    public List<Timestamp> getFifteenMinuteChunks() {
        ArrayList<Timestamp> chunks = new ArrayList<>();
        if(!startTime.before(endTime)){
            return chunks;
        }
        Timestamp chunkStart = (Timestamp) startTime.clone();
        while(chunkStart.before(endTime)){
            chunks.add(chunkStart);
            chunkStart = new Timestamp(chunkStart.toInstant().plusSeconds(60 * 15).toEpochMilli());
        }
        return chunks;
    }


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
