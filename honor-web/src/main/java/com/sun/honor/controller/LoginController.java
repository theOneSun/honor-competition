package com.sun.honor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunjian.
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/hello")
    public String sayHello(){
        return "hello";
    }
}
