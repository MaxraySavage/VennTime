package org.launchcode.VennTime.controllers;
import org.launchcode.VennTime.data.AttendeeRepository;
import org.launchcode.VennTime.data.EventRepository;
import org.launchcode.VennTime.data.TimeChunkRepository;
import org.launchcode.VennTime.models.Attendee;
import org.launchcode.VennTime.models.Event;
import org.launchcode.VennTime.models.TimeChunk;
import org.launchcode.VennTime.models.dto.ViewEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;


@Controller
@RequestMapping(value = "/viewEvent")
public class ViewEventController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    TimeChunkRepository timeChunkRepository;

    @Autowired
    AttendeeRepository attendeeRepository;

    @GetMapping("/{id}")
    public String displayViewEventForm (HttpServletRequest request, Model model, @PathVariable("id") String id) {
        int idInt = Integer.parseInt(id,10);
        Optional<Event> optionalEvent = eventRepository.findById(idInt);
        if(optionalEvent.isEmpty()){
            // If event not found redirect to home
            return "redirect:";
        }
        Event event = optionalEvent.get();
        model.addAttribute("title", "View Event");
        model.addAttribute("event", event);
        model.addAttribute("attendee", new Attendee());
        model.addAttribute("viewEventDTO", new ViewEventDTO());


        return "viewEvent";
    }

    @PostMapping("/{id}")
    public String processViewEventForm (
            HttpServletRequest request,
            Model model,
            @PathVariable("id") String id,
            @ModelAttribute @Valid ViewEventDTO viewEventDTO
    ) {
        int idInt = Integer.parseInt(id,10);
        Optional<Event> optionalEvent = eventRepository.findById(idInt);
        if(optionalEvent.isEmpty()){
            // If event not found redirect to home
            return "redirect:";
        }

        Event event = optionalEvent.get();

        Attendee attendee = new Attendee();
        attendee.setName(viewEventDTO.getName());
        Attendee savedAttendee = attendeeRepository.save(attendee);

        event.getAttendees().add(savedAttendee);
        eventRepository.save(event);

        ArrayList<TimeChunk> timeChunks = new ArrayList<>();

        for(String item : viewEventDTO.getAttendeeAvailableChunksList()){
            int chunkId = Integer.parseInt(item,10);
            Optional timeChunkOptional = timeChunkRepository.findById(chunkId);
            if(timeChunkOptional.isEmpty()) {
                continue;
            }
            TimeChunk timeChunk = (TimeChunk) timeChunkOptional.get();
            timeChunk.getAvailableAttendees().add(savedAttendee);
            timeChunkRepository.save(timeChunk);
        }


        return "redirect:/viewEvent/" + id;
    }

}
