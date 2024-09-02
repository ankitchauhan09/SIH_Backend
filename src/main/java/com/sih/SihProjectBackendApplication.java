package com.sih;

import com.sih.entities.UserRole;
import com.sih.repositories.UserRoleRepo;
import com.sih.utils.Constants;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.ModelMap;

import java.util.List;

@SpringBootApplication
public class SihProjectBackendApplication implements CommandLineRunner {

    @Autowired
    private UserRoleRepo userRoleRepo;

    Logger logger = LoggerFactory.getLogger(SihProjectBackendApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(SihProjectBackendApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            if (userRoleRepo.count() == 0) {
                UserRole role1 = new UserRole();
                role1.setRoleId(Constants.USER_ROLE_JOB_SEEKER);
                role1.setRoleName("JOB_SEEKER");

                UserRole role2 = new UserRole();
                role2.setRoleId(Constants.USER_ROLE_RECRUITER);
                role2.setRoleName("RECRUITER");

                List<UserRole> roleResult = this.userRoleRepo.saveAll(List.of(role1, role2));
                this.logger.info("Roles created : {}", roleResult);
            } else {
                this.logger.info("Roles already exist in the database.");
            }
        } catch (Exception e) {
            this.logger.error("Error creating roles: {}", e.getMessage(), e);
        }
    }
}
