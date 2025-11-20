package com.task_mini.task_mini.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.task_mini.task_mini.dto.auth.DTOLoginRequest;
import com.task_mini.task_mini.dto.auth.DTORegisterRequest;
import com.task_mini.task_mini.dto.user.DTOUserResponse;
import com.task_mini.task_mini.models.RoleEntity;
import com.task_mini.task_mini.models.RoleEnum;
import com.task_mini.task_mini.models.UserEntity;
import com.task_mini.task_mini.repository.RoleRepository;
import com.task_mini.task_mini.repository.UserRepository;
import com.task_mini.task_mini.service.impl.UserEntityImpl;

@Service
public class UserEntityService implements UserEntityImpl{

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepo;

    @Override
    public void createUserEntity(DTORegisterRequest dtoRegisterRequest) {

        RoleEntity roleUser = roleRepo.findByRoleEnum(RoleEnum.USER)
        .orElseThrow(()-> new RuntimeException("ROLE no funcionando"));

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(dtoRegisterRequest.getUsername());
        userEntity.setEmail(dtoRegisterRequest.getEmail());
        userEntity.setPassword(encoder.encode(dtoRegisterRequest.getPassword()));
        userEntity.setRoles(Set.of(roleUser));
        userRepo.save(userEntity);
    }

    @Override
    public Set<DTOUserResponse> listaUsuarios() {
        Set<DTOUserResponse> listaUsuarios = new HashSet<>();
        List<UserEntity> usuariosListaBd = userRepo.findAll();
        for (UserEntity user : usuariosListaBd) {
            DTOUserResponse dtoUserResponse = new DTOUserResponse();
            dtoUserResponse.setId(user.getId());
            dtoUserResponse.setUsername(user.getUsername());
            dtoUserResponse.setEmail(user.getEmail());
            listaUsuarios.add(dtoUserResponse);
        }
        return listaUsuarios;
    }

    @Override
    public DTOUserResponse traerUserPorId(Long id) {
        DTOUserResponse dtoUserResponse = new DTOUserResponse();

        var opt = userRepo.findById(id);
        if (opt.isEmpty()) return null;
        UserEntity userEntity = opt.get();
        
        dtoUserResponse.setId(userEntity.getId());
        dtoUserResponse.setUsername(userEntity.getUsername());
        dtoUserResponse.setEmail(userEntity.getEmail());
        return dtoUserResponse;
    }

    @Override
    public void eliminarUsuario(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public void editar(Long id,DTORegisterRequest dtoRegisterRequest) {
        UserEntity user = userRepo.findById(id)
        .orElseThrow(()-> new RuntimeException("No se encuentra al usuario"));

        user.setUsername(dtoRegisterRequest.getUsername());
        user.setEmail(dtoRegisterRequest.getEmail());
        
        if (dtoRegisterRequest.getPassword() != null && !dtoRegisterRequest.getPassword().isBlank()) {
            user.setPassword(encoder.encode(dtoRegisterRequest.getPassword()));
        }

        userRepo.save(user);
    }

    @Override
    public UserEntity login(DTOLoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        UserEntity user = userRepo.findUserEntityByUsername(username)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        
        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }
        return user;
    }


}
