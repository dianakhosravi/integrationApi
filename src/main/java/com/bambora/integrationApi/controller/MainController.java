package com.bambora.integrationApi.controller;

import com.bambora.integrationApi.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainController {


    @GetMapping("/")
    public ModelAndView startApp(@ModelAttribute User user) {
        return new ModelAndView("index.html");
    }


}
