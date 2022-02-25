package org.launchcode.VennTime.models;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class Attendee extends AbstractEntity{

    private String name;

    @ManyToOne
    private Event event;

    @ManyToMany(mappedBy = "availableAttendees")
    private List<TimeChunk> timeChunks;

    public Attendee() {}

    public Attendee(String name, List<TimeChunk> timeChunks) {
        this.name = name;
        this.timeChunks = timeChunks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<TimeChunk> getTimeChunks() {
        return timeChunks;
    }

    public void setTimeChunks(List<TimeChunk> timeChunks) {
        this.timeChunks = timeChunks;
    }
}
