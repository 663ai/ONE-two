package com.lxl.demo.controller;

import com.lxl.demo.entities.User;
import com.lxl.demo.service.UserService;
import com.lxl.demo.util.MD5Util;
import org.omg.CORBA.StringHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.Random;

@Controller
public class RegisterController{
    @Autowired
    public UserService userService;
    private static Logger log = LoggerFactory.getLogger(RegisterController.class);

    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public ModelAndView toRegister(ModelMap modelMap){
        User user = new User();
        return new ModelAndView("register").addObject(user);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView regieter(@ModelAttribute(value = "user")@Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ModelAndView("register");
        }
        log.info("---------------------"+user.getPassword());
        String md5password = MD5Util.inputToDb(user.getPassword(), "user");
        user.setDbflag("user");

//        user.setId(2018);
        user.setPassword(md5password);
        User newUser=userService.regist(user);
        if(newUser != null){
            return new ModelAndView("home");
        }else {
            return new ModelAndView("register");
        }
    }
}

