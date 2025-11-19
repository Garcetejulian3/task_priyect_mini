package com.task_mini.task_mini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task_mini.task_mini.dto.auth.DTOLoginRequest;
import com.task_mini.task_mini.dto.auth.DTOLoginResponse;
import com.task_mini.task_mini.dto.auth.DTORegisterRequest;
import com.task_mini.task_mini.service.UserEntityService;

@RestController
@RequestMapping("/users")
public class UserEntityController {

    @Autowired
    UserEntityService userService;


    @PostMapping("/register")
    public void createUser(@RequestBody DTORegisterRequest dto) {
        userService.createUserEntity(dto);
    }

    @PostMapping("/ogin")
     public DTOLoginResponse login(@RequestBody DTOLoginRequest request) {

        userService.login(request);

        // Aquí Spring Security crea la sesión automáticamente
        // cuando la autenticación es exitosa.

        return new DTOLoginResponse("Login exitoso", request.getUsername());
    }

}
