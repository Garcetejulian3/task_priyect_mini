package com.task_mini.task_mini.service.impl;

import java.util.Set;

import com.task_mini.task_mini.dto.task.DTOTaskCreate;
import com.task_mini.task_mini.dto.task.DTOTaskResponse;
import com.task_mini.task_mini.dto.task.DTOTaskUpdate;

public interface TaskServiceImpl {

    // metodo para crear una tarea
    public void createTaskEntity( DTOTaskCreate dtoTaskCreate);
    // metodo para traer lista de tareas
    public Set<DTOTaskResponse> listaDeTask();
    // metodo para traer por id
    public DTOTaskResponse traerTaskPorId(Long id);
    // metodo para eliminar 
    public void eliminarTask(Long id);
    // metodo para editar 
    public void editar(Long id,DTOTaskUpdate dtoTaskUpdate);
}
