package org.launchcode.VennTime.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event extends AbstractEntity{

    @NotBlank(message= "Name field is required")
    @Size(min=2, max=50, message= "Name must be at least 3 characters long.")
    private String name;

    @NotBlank(message= "Event description is required")
    @Size(min=7, max=500, message= "Add a description of event")
    private String description;

    @OneToMany
    private List<TimeChunk> timeChunks;

    @OneToMany
    private List<Attendee> attendees;

    public Event() {
        this.timeChunks = new ArrayList<TimeChunk>();
        this.attendees = new ArrayList<Attendee>();
    }

    public Event(String name, String description, List<TimeChunk> timeChunks, List<Attendee> attendees) {
        this.name = name;
        this.description = description;
        this.timeChunks = timeChunks;
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

    public List<TimeChunk> getTimeChunks() {
        return timeChunks;
    }

    public void setTimeChunks(List<TimeChunk> timeChunks) {
        this.timeChunks = timeChunks;
    }

    public List<Attendee> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<Attendee> attendees) {
        this.attendees = attendees;
    }
}
