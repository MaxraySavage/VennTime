package org.launchcode.VennTime.controllers;

import org.launchcode.VennTime.data.EventRepository;
import org.launchcode.VennTime.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @Autowired
    EventRepository eventRepository;

    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("event", new Event());
        return "home";
    }

    @ResponseBody
    @PostMapping("/")
    public String processCreateEvent(Model model, @ModelAttribute Event newEvent){
        eventRepository.save(newEvent);
        return newEvent.getName();
    }

}
