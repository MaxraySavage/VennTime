package org.launchcode.VennTime.models.mapper;

import org.launchcode.VennTime.models.Event;
import org.launchcode.VennTime.models.dto.CreateEventDTO;
import org.springframework.stereotype.Component;

@Component
public class DTOMapper {

    public Event toEvent(CreateEventDTO createEventDTO) {
        Event newEvent = new Event();
        newEvent.setName(createEventDTO.getName());
        newEvent.setDescription(createEventDTO.getDescription());
        return newEvent;
    }
}
