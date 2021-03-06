package org.launchcode.VennTime.models;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TimeChunk extends AbstractEntity {

    public TimeChunk() {
        this.availableAttendees = new ArrayList<>();
    }

    public TimeChunk(ZonedDateTime startTime, ZonedDateTime endTime) {
        this.availableAttendees = new ArrayList<>();
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @ManyToOne
    private Event event;

    @ManyToMany
    private List<Attendee> availableAttendees;

    private ZonedDateTime startTime;

    private ZonedDateTime endTime;

    public String getAttendeeListString() {
        String result = "";
        for(Attendee attendee: availableAttendees){
            result += attendee.getName() + ",";
        }
        return result;

    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(ZonedDateTime endTime) {
        this.endTime = endTime;
    }

    public List<Attendee> getAvailableAttendees() {
        return availableAttendees;
    }

    public void setAvailableAttendees(List<Attendee> availableAttendees) {
        this.availableAttendees = availableAttendees;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public long getStartingEpochDay() {
        return startTime.getLong(ChronoField.EPOCH_DAY);
    }
}
