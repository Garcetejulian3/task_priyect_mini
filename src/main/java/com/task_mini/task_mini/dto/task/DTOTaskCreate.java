package com.task_mini.task_mini.dto.task;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DTOTaskCreate {

    private String title;
    private String description;
    private LocalDateTime alarmAt; 
}
