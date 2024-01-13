package com.example.loginexample.controllers;


import com.example.loginexample.services.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DemoController {


    private final AuthServiceImpl authService;

    @GetMapping(value = "/hello")
    public String hello() {
        return "Hello World!";
    }


    @GetMapping(value = "/hello2")
    public String hello2() {
        return "Hello World Secured!";
    }


}
