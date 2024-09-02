package com.sih.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer job_role_id;
    private String job_role_name;
}
