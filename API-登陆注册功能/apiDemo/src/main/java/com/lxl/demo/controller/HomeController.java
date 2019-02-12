package com.lxl.demo.controller;

import com.lxl.demo.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    private static Logger logger = LoggerFactory.getLogger(HomeController.class);
    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String login(Model model, User user){
        logger.info(user.getUsername());
        model.addAttribute("user",user);
        return "home";
    }
}
