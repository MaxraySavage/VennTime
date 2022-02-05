package org.launchcode.VennTime.controllers;
import org.launchcode.VennTime.data.EventRepository;
import org.launchcode.VennTime.models.AbstractEntity;
import org.launchcode.VennTime.models.Attendee;
import org.launchcode.VennTime.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping(value = "/viewEvent")
public class ViewEventController {

    @Autowired
    EventRepository eventRepository;

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
        return "viewEvent";
    }

}
