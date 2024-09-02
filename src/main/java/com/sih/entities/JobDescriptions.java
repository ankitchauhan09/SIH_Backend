package com.sih.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobDescriptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long job_id;
    @OneToOne
    @JoinColumn(name = "job_role_id", referencedColumnName = "job_role_id")
    private JobRoles role;

}
