package com.task_mini.task_mini.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DTOLoginRequest {

    private String username;
    private String password;
}
