package com.lxl.demo.base.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
@CrossOrigin(origins = "*",allowCredentials = "true")
public class BaseApiController {

}
