package com.task_mini.task_mini.dto.task;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DTOTaskResponse {

    private Long id;
    private String title;
    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime alarmAt; 

    private boolean completed;
    
    private String username;
}
