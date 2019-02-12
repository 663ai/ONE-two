package com.lxl.demo.controller;

import com.lxl.demo.entities.User;
import com.lxl.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.util.StringUtils;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        System.out.println("GET请求");
        model.addAttribute("user", new User());
        model.addAttribute("title", "登陆页面");
        return "login";
    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String login(@Valid User user, BindingResult bindingResult, HttpSession session, Model model, String code) {
//        System.out.println("POST请求");
//        if (bindingResult.hasErrors()) {
//            System.out.println(bindingResult.hasErrors());
//            return "login";
//        }
//        String sessionCode = (String) session.getAttribute("code");
//        if (!StringUtils.equalsIgnoreCase(code, sessionCode)) {
//            model.addAttribute("message", "验证码不正确");
//            return "login";
//        }
//        logger.info("------------------------" + user.getPassword());
//        User temp = userService.getUser(user.getUsername());
//        if (temp != null) {
//            String inputPassword = MD5Util.inputToDb(user.getPassword(), temp.getDbflag());
//            System.out.println("-------------测试1--------------");
//            System.out.println(inputPassword);
//            System.out.println(temp.getPassword());
//            System.out.println(user.getPassword());
//            System.out.println(temp.getDbflag());
//
//            if (temp.getPassword().equals(inputPassword)) {
//                System.out.println(111111);
//                session.setAttribute("user", temp);
//                return "home";
//            } else {
//                System.out.println(2222222);
//                return "login";
//            }
//        } else {
//            System.out.println(333333);
//            return "login";
//        }
//    }

}