package com.task_mini.task_mini.dto.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DTOUserResponse {

    private Long id;
    private String username;
    private String email;
}
