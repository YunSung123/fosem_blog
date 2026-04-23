package com.tenco.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    // http://localhost:8080/user/join-form
    @GetMapping("/user/join-form")
    public String joinForm() {
        return "user/join-form";
    }
}
