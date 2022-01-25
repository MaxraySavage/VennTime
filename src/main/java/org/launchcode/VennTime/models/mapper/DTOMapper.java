package org.launchcode.VennTime.models.mapper;

import org.launchcode.VennTime.models.AvailabilityRange;
import org.launchcode.VennTime.models.Event;
import org.launchcode.VennTime.models.dto.CreateEventDTO;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Component
public class DTOMapper {

    public Event toEvent(CreateEventDTO createEventDTO) {
        Event newEvent = new Event();
        newEvent.setName(createEventDTO.getName());
        newEvent.setDescription(createEventDTO.getDescription());
        Instant startInstant = createEventDTO.getDate().atTime(12, 30).atZone(ZoneId.of("America/New_York")).toInstant();
        Instant endInstant = startInstant.plusSeconds(3600);
        Timestamp startTime = new Timestamp(startInstant.toEpochMilli());
        Timestamp endTime = new Timestamp(endInstant.toEpochMilli());
        AvailabilityRange availabilityRange = new AvailabilityRange(startTime, endTime);
        newEvent.getAvailabilityRanges().add(availabilityRange);
        return newEvent;
    }
}
