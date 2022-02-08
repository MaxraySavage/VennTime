package org.launchcode.VennTime.models.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

public class CreateEventDTO {

    @NotBlank(message= "Name field is required")
    @Size(min=2, max=50, message= "Name must be at least 3 characters long.")
    private String name;

    @NotBlank(message= "Event description is required")
    @Size(min=7, max=500, message= "Add a description of event")
    private String description;

    @NotBlank(message= "Must select at least one date")
    private String selectedDates;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime startTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime endTime;

    private String timezone;

    private static final List<String> possibleTimezones = new ArrayList<>(ZoneId.getAvailableZoneIds());

    public CreateEventDTO() {
        Collections.sort(possibleTimezones);
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

    public String getSelectedDates() {
        return selectedDates;
    }

    public void setSelectedDates(String selectedDates) {
        this.selectedDates = selectedDates;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public List<String> getPossibleTimezones() {
        return possibleTimezones;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
