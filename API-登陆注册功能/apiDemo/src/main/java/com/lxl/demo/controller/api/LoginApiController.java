package com.lxl.demo.controller.api;

import com.lxl.demo.VO.UserVO;
import com.lxl.demo.base.controller.BaseApiController;
import com.lxl.demo.base.result.Result;
import com.lxl.demo.base.result.ResultCode;
import com.lxl.demo.entities.User;
import com.lxl.demo.service.UserService;
import com.lxl.demo.util.MD5Util;
import com.lxl.demo.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class LoginApiController extends BaseApiController {

    private static Logger log = LoggerFactory.getLogger(LoginApiController.class);

    @Autowired
    public UserService userService;

    //	@ResponseBody
    @RequestMapping(value="/login")
    public Result<Object> login(@ModelAttribute(value="user") @Valid User user, BindingResult bindingResult, HttpSession session, String code, Model model, HttpServletResponse response){
        log.info("username="+user.getUsername()+";password="+user.getPassword());
        if(bindingResult.hasErrors()){
            return Result.failure();	// 500, "error"
        }
        UserVO dbUser = userService.getUser(user.getUsername());
        if(dbUser != null){
            if(dbUser.getPassword().equals(MD5Util.inputToDb(user.getPassword(), dbUser.getDbflag()))){
                //session.setAttribute("user", dbUser);
                String token = UUIDUtil.getUUID();
                userService.saveUserToRedisByToken(dbUser, token);

                Cookie cookie = new Cookie("token", token);
                cookie.setMaxAge(3600);
                cookie.setPath("/");
                response.addCookie(cookie);

                return Result.success();	// Result.success(); 200, "success"
            }else{
                return Result.failure(ResultCode.USER_LOGIN_ERROR);
            }
        }else{
            return Result.failure(ResultCode.USER_LOGIN_ERROR);
        }

    }
}