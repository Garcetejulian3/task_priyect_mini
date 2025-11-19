package com.task_mini.task_mini;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.task_mini.task_mini.models.PermisionEntity;
import com.task_mini.task_mini.models.RoleEntity;
import com.task_mini.task_mini.models.RoleEnum;
import com.task_mini.task_mini.models.UserEntity;
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

            // CREACION DE PERMISOS
            PermisionEntity createPermission = PermisionEntity.builder()
            .name("CREATE")
            .build();

            PermisionEntity readPermission = PermisionEntity.builder()
            .name("READ")
            .build();

            PermisionEntity updatePermission = PermisionEntity.builder()
            .name("UPDATE")
            .build();

            PermisionEntity deletePermission = PermisionEntity.builder()
            .name("DELETE")
            .build();

            PermisionEntity refactorPermission = PermisionEntity.builder()
            .name("REFACTOR")
            .build();

			// CREACION DE ROLES

            RoleEntity roleAdmin = RoleEntity.builder()
            .roleEnum(RoleEnum.ADMIN)
            .permisosList(Set.of(createPermission,readPermission,updatePermission,deletePermission))
            .build();

            RoleEntity roleUser = RoleEntity.builder()
            .roleEnum(RoleEnum.USER)
            .permisosList(Set.of(createPermission,readPermission,updatePermission,deletePermission))
            .build();

			// Guardar roles si no existen
			if (roleRepository.findByRoleEnum(RoleEnum.ADMIN).isEmpty()) {
				roleRepository.save(roleAdmin);
			}

			if (roleRepository.findByRoleEnum(RoleEnum.USER).isEmpty()) {
				roleRepository.save(roleUser);
			}

			// creo usuario ADMIN
			if(userRepository.findUserEntityByUsername("admin").isEmpty()){
				UserEntity admin = new UserEntity();
				admin.setUsername("admin");
				admin.setEmail("admin@gmail.com");
				admin.setPassword("$2a$10$sOq61UZtD2NBNra7zluNeOL4ovetthSlsdBYU.OMH4R5yLGGF.8Ay");
				admin.setRoles(Set.of(roleAdmin));

				userRepository.save(admin);
			}

        };
    }

}
