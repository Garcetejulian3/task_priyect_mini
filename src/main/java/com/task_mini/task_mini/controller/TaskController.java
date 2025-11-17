package com.task_mini.task_mini.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/task")
public class TaskController {

    @GetMapping("/home")
    public String getMethodName() {
        return "Hola Home Task";
    }
    
}
