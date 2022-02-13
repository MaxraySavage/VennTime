package org.launchcode.VennTime.controllers;

import org.launchcode.VennTime.data.EventRepository;
import org.launchcode.VennTime.models.Event;
import org.launchcode.VennTime.models.dto.CreateEventDTO;
import org.launchcode.VennTime.models.mapper.DTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;

@Controller
public class HomeController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    private DTOMapper dtoMapper;

    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("createEventDTO", new CreateEventDTO());
        return "home";
    }


    @PostMapping("/")
    public String processCreateEvent(Model model, @ModelAttribute @Valid CreateEventDTO createEventDTO, Errors errors) throws ParseException {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            model.addAttribute("createEventDTO", createEventDTO);
            return "home";
        }

        LocalTime startTime = createEventDTO.getStartTime();
        LocalTime endTime = createEventDTO.getEndTime();

        if (startTime.isAfter(endTime)) {
            errors.rejectValue("startTime", "startTime.isAfterEndTime", "Please enter a valid start and end time.");
            model.addAttribute("title", "Create Event");
            return "home";
        }

        Event newEvent = dtoMapper.toEvent(createEventDTO);
        Event savedEvent = eventRepository.save(newEvent);
        return "redirect:viewEvent/" + savedEvent.getId();
    }

}

