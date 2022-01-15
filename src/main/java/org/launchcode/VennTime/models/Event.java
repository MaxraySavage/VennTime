package org.launchcode.VennTime.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Event extends AbstractEntity{

    private String name;

    private String description;

    @ElementCollection
    private List<AvailabilityRange> availabilityRanges;

    @OneToMany
    private List<Attendee> attendees;

    public Event() {}

    public Event(String name, String description, List<AvailabilityRange> availabilityRanges, List<Attendee> attendees) {
        this.name = name;
        this.description = description;
        this.availabilityRanges = availabilityRanges;
        this.attendees = attendees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AvailabilityRange> getAvailabilityRanges() {
        return availabilityRanges;
    }

    public void setAvailabilityRanges(List<AvailabilityRange> availabilityRanges) {
        this.availabilityRanges = availabilityRanges;
    }

    public List<Attendee> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<Attendee> attendees) {
        this.attendees = attendees;
    }
}
