package org.launchcode.VennTime.models;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Attendee extends AbstractEntity{

    private String name;

    @ManyToOne
    private Event event;

    @ElementCollection
    private List<AvailabilityRange> availabilityRanges;

    public Attendee() {}

    public Attendee(String name, List<AvailabilityRange> availabilityRanges) {
        this.name = name;
        this.availabilityRanges = availabilityRanges;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AvailabilityRange> getAvailabilityRanges() {
        return availabilityRanges;
    }

    public void setAvailabilityRanges(List<AvailabilityRange> availabilityRanges) {
        this.availabilityRanges = availabilityRanges;
    }
}
