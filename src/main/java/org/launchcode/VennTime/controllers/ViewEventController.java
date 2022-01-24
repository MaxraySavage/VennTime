package org.launchcode.VennTime.controllers;
import org.launchcode.VennTime.data.EventRepository;
import org.launchcode.VennTime.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ViewEventController {

    private static List<Event> event = new ArrayList<>();

    @Autowired
    EventRepository eventRepository;

    @GetMapping
    public String displayEventsById(Model model) {
        model.addAttribute("event", event);
        return "event";
    }
    @PostMapping("create")
    public String processCreateEventForm(@RequestParam String eventName,
                                         @RequestParam String eventDescription) {
        event.add(new Event());
        return "";
    }
}
