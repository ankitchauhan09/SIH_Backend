package com.sih.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_role")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRole {
    @Id
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "role_name", unique = true)
    private String roleName;
}
