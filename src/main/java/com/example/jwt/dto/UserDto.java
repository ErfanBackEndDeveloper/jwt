package com.example.jwt.dto;

import com.example.jwt.entity.Role;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private Boolean isEnabled;
    private List<Role> roles = new ArrayList<>();
}


