package com.sih.repositories;

import com.sih.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, Integer> {
    Optional<UserRole> findByRoleName(String roleName);
    Optional<UserRole> findByRoleId(Integer roleId);
}
