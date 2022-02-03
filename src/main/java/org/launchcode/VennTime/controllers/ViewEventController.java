package org.launchcode.VennTime.controllers;
import org.launchcode.VennTime.data.EventRepository;
import org.launchcode.VennTime.models.AbstractEntity;
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
public class ViewEventController {

    @Autowired
    EventRepository eventRepository;

//    @RequestMapping("")
//    public String displayAllPlacesTraveled(Model model, @PathVariable int eventId){
//        model.addAttribute("title", "view event");
//        model.addAttribute("places", eventRepository.findById(eventId));
//        return "viewEvent";
//    }

//    @GetMapping("viewEvent/{eventId}")
    @PostMapping("{id}")
    public String displayViewEventForm (HttpServletRequest request, Model model, @PathVariable("id") int id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        model.addAttribute("title", "View Event");
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            model.addAttribute("event", eventRepository.findById(id));
        }
        return "viewEvent";
    }

    @GetMapping("")
    public String  processViewEventForm(@RequestParam int id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);

     return "redirect:";

    }
    }
