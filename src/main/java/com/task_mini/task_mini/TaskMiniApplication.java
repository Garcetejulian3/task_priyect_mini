package com.task_mini.task_mini;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.task_mini.task_mini.repository.PermisionRepository;
import com.task_mini.task_mini.repository.RoleRepository;
import com.task_mini.task_mini.repository.UserRepository;

@SpringBootApplication
public class TaskMiniApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskMiniApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository,PermisionRepository permisionRepository,RoleRepository roleRepository){
        return args -> {
            // Desactivado temporalmente para evitar errores de cascada con entidades detached
            // TODO: Implementar correctamente con transacciones y validaciones
        };
    }

}
