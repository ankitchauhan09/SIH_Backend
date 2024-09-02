package com.sih.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobApplications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long application_id;
    @ManyToOne
    private User user;
    private Integer role_id;
    private String resume_id;
    private Integer application_status;
    @ManyToOne
    private JobDescriptions job_descriptions;
}
