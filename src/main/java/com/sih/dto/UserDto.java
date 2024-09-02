package com.sih.dto;

import com.sih.entities.Address;
import com.sih.entities.JobApplications;
import com.sih.entities.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
