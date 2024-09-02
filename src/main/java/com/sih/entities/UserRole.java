package com.sih.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRole {
    @Id
    @Column(name = "role_id")
    private Integer roleId;
    private String roleName;
}
