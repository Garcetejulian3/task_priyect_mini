package com.task_mini.task_mini.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.task_mini.task_mini.dto.task.DTOTaskCreate;
import com.task_mini.task_mini.dto.task.DTOTaskResponse;
import com.task_mini.task_mini.dto.task.DTOTaskUpdate;
import com.task_mini.task_mini.models.Task;
import com.task_mini.task_mini.models.UserEntity;
import com.task_mini.task_mini.repository.TaskRepository;
import com.task_mini.task_mini.repository.UserRepository;
import com.task_mini.task_mini.service.impl.TaskServiceImpl;

@Service
public class TaskService implements TaskServiceImpl {

    private String getLoggedUsername() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }

    @Autowired
    TaskRepository taskRepo;

    @Autowired
    UserRepository userRepo;

    @Override
    public void createTaskEntity(DTOTaskCreate dtoTaskCreate) {
        // obtener el usuario logeado desde la sesion
        String username = getLoggedUsername();

        // busco el usuario en la base de datos
        UserEntity userEntity = userRepo.findUserEntityByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // creo la tarea por fin
        Task task = new Task();
        task.setTitle(dtoTaskCreate.getTitle());
        task.setDescription(dtoTaskCreate.getDescription());
        task.setAlarmAt(dtoTaskCreate.getAlarmAt());
        task.setCompleted(false);
        task.setCreatedAt(LocalDateTime.now());
        task.setUserDueno(userEntity);

        taskRepo.save(task);

    }

    @Override
    public Set<DTOTaskResponse> listaDeTask() {

        // usuario logueado
        String username = getLoggedUsername();

        Set<DTOTaskResponse> listaDeTask = new HashSet<>();
        List<Task> taskListBD = taskRepo.findAllByUserDuenoUsername(username);
        for (Task task : taskListBD) {
            DTOTaskResponse dtoTaskResponse = new DTOTaskResponse();
            dtoTaskResponse.setId(task.getId());
            dtoTaskResponse.setTitle(task.getTitle());
            dtoTaskResponse.setDescription(task.getDescription());
            dtoTaskResponse.setCreatedAt(task.getCreatedAt());
            dtoTaskResponse.setAlarmAt(task.getAlarmAt());
            dtoTaskResponse.setCompleted(task.isCompleted());
            dtoTaskResponse.setUsername(task.getUserDueno().getUsername());
            listaDeTask.add(dtoTaskResponse);
        }
        return listaDeTask;
    }

    @Override
    public DTOTaskResponse traerTaskPorId(Long id) {

        // usuario logueado
        String username = getLoggedUsername();

        Task task = taskRepo.findByIdAndUserDuenoUsername(id, username)
                .orElseThrow(() -> new RuntimeException("No tienes permiso para ver esta tarea"));

        DTOTaskResponse dtoTaskResponse = new DTOTaskResponse();
        dtoTaskResponse.setId(task.getId());
        dtoTaskResponse.setTitle(task.getTitle());
        dtoTaskResponse.setDescription(task.getDescription());
        dtoTaskResponse.setCreatedAt(task.getCreatedAt());
        dtoTaskResponse.setAlarmAt(task.getAlarmAt());
        dtoTaskResponse.setCompleted(task.isCompleted());
        dtoTaskResponse.setUsername(task.getUserDueno().getUsername());
        return dtoTaskResponse;
    }

    @Override
    public void eliminarTask(Long id) {
        String username = getLoggedUsername();
        Task task = taskRepo.findByIdAndUserDuenoUsername(id, username)
                .orElseThrow(() -> new RuntimeException("No tienes permiso para eliminar esta tarea"));
        taskRepo.delete(task);
    }

    @Override
    public void editar(Long id,DTOTaskUpdate dtoTaskUpdate) {
        // usuario logueado
        String username = getLoggedUsername();

        // buscar la tarea del usuario
        Task task = taskRepo.findByIdAndUserDuenoUsername(id, username)
                .orElseThrow(() -> new RuntimeException("No tienes permiso para editar esta tarea"));

        
        if (dtoTaskUpdate.getTitle() != null) {
            task.setTitle(dtoTaskUpdate.getTitle());
        }

        if (dtoTaskUpdate.getDescription() != null) {
            task.setDescription(dtoTaskUpdate.getDescription());
        }

        if (dtoTaskUpdate.getAlarmAt() != null) {
            task.setAlarmAt(dtoTaskUpdate.getAlarmAt());
        }

        task.setCompleted(dtoTaskUpdate.isCompleted());

        // guardar cambios
        taskRepo.save(task);
    }
}
