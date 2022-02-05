package org.launchcode.VennTime.models.mapper;

import org.launchcode.VennTime.models.AvailabilityRange;
import org.launchcode.VennTime.models.Event;
import org.launchcode.VennTime.models.dto.CreateEventDTO;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class DTOMapper {

    public Event toEvent(CreateEventDTO createEventDTO) throws ParseException {
        Event newEvent = new Event();
        newEvent.setName(createEventDTO.getName());
        newEvent.setDescription(createEventDTO.getDescription());

        String formSelectedDates = createEventDTO.getSelectedDates();
        List<String> dateStringArray = Arrays.asList(formSelectedDates.split(",", -1));

        for(String dateString : dateStringArray) {
            LocalDate currentDate = LocalDate.parse(dateString);

            Instant startInstant = currentDate
                    .atTime(createEventDTO.getStartTime())
                    .atZone(ZoneId.of(createEventDTO.getTimezone()))
                    .toInstant();
            Instant endInstant = currentDate
                    .atTime(createEventDTO.getEndTime())
                    .atZone(ZoneId.of(createEventDTO.getTimezone()))
                    .toInstant();

            Timestamp startTime = new Timestamp(startInstant.toEpochMilli());
            Timestamp endTime = new Timestamp(endInstant.toEpochMilli());

            AvailabilityRange availabilityRange = new AvailabilityRange(startTime, endTime);
            newEvent.getAvailabilityRanges().add(availabilityRange);
        }

        return newEvent;
    }
}
