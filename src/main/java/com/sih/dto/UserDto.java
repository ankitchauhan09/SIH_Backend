package com.sih.dto;

import com.sih.entities.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UserDto {
    private Integer id;
    private String name;
    private String email;
    private String contact;
    private String password;
    private String roleName;
    private UserRole role;
}
