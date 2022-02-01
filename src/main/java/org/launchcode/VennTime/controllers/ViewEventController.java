package org.launchcode.VennTime.controllers;
import org.launchcode.VennTime.data.EventRepository;
import org.launchcode.VennTime.models.AbstractEntity;
import org.launchcode.VennTime.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class ViewEventController {

    @Autowired
    EventRepository eventRepository;

    @GetMapping("viewEvent/{eventId)")
    public String displayViewEventForm (HttpServletRequest request, Model model, @PathVariable int eventId) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        model.addAttribute("title", "View Event");
        Event event = optionalEvent.get();
        model.addAttribute("event", event);


        return "/viewEvent";
    }

    @PostMapping("viewEvent")
    public String  processViewEventForm(@RequestParam int id) {
//        Optional<Event> optionalEvent = eventRepository.findById(id);

     return "redirect:";

    }
    }
