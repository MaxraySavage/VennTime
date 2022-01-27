package org.launchcode.VennTime.models.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class CreateEventDTO {

    @NotBlank(message= "Name field is required")
    @Size(min=2, max=50, message= "Name must be at least 3 characters long.")
    private String name;

    @NotBlank(message= "Event description is required")
    @Size(min=7, max=500, message= "Add a description of event")
    private String description;

    @NotBlank(message= "Select a Date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public CreateEventDTO() {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
