package org.launchcode.VennTime.models.mapper;

import org.launchcode.VennTime.data.EventRepository;
import org.launchcode.VennTime.data.TimeChunkRepository;
import org.launchcode.VennTime.models.TimeChunk;
import org.launchcode.VennTime.models.Event;
import org.launchcode.VennTime.models.dto.CreateEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class DTOMapper {

    @Autowired
    TimeChunkRepository timeChunkRepository;

    @Autowired
    EventRepository eventRepository;

    public Event toEvent(CreateEventDTO createEventDTO) throws ParseException {
        Event newEvent = new Event();
        newEvent.setName(createEventDTO.getName());
        newEvent.setDescription(createEventDTO.getDescription());

        Event savedEvent = eventRepository.save(newEvent);

        String formSelectedDates = createEventDTO.getSelectedDates();
        List<String> dateStringArray = Arrays.asList(formSelectedDates.split(",", -1));

        for(String dateString : dateStringArray) {
            LocalDate currentDate = LocalDate.parse(dateString);

            ZonedDateTime startTime = currentDate
                    .atTime(createEventDTO.getStartTime())
                    .atZone(ZoneId.of(createEventDTO.getTimezone()));
            ZonedDateTime endTime = currentDate
                    .atTime(createEventDTO.getEndTime())
                    .atZone(ZoneId.of(createEventDTO.getTimezone()));

            while(startTime.plusMinutes(15).isBefore(endTime)) {
                TimeChunk timeChunk = new TimeChunk(startTime, startTime.plusMinutes(15));
                TimeChunk savedTimeChunk = timeChunkRepository.save(timeChunk);
                savedEvent.getTimeChunks().add(savedTimeChunk);
                startTime = startTime.plusMinutes(15);
            }

            TimeChunk timeChunk = new TimeChunk(startTime, endTime);
            TimeChunk savedTimeChunk = timeChunkRepository.save(timeChunk);
            savedEvent.getTimeChunks().add(savedTimeChunk);
            savedEvent = eventRepository.save(savedEvent);

        }

        return savedEvent;
    }
}
