package com.task_mini.task_mini.service.impl;

import java.util.Set;

import com.task_mini.task_mini.dto.auth.DTORegisterRequest;
import com.task_mini.task_mini.dto.user.DTOUserResponse;

public interface UserEntityImpl {

    // metodo para crear el usuario 
    public void createUserEntity( DTORegisterRequest dtoRegisterRequest);
    // metodo para traer lista de usuarios
    public Set<DTOUserResponse> listaUsuarios();
    // metodo para traer por id
    public DTOUserResponse traerUserPorId(Long id);
    // metodo para eliminar 
    public void eliminarUsuario(Long id);
    // metodo para editar 
    public void editar(DTORegisterRequest dtoRegisterRequest);
}
