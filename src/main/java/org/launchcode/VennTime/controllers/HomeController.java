package org.launchcode.VennTime.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("event", new Event());
        return "home";
    }
}
