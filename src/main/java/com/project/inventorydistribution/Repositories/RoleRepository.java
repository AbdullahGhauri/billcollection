package com.project.inventorydistribution.Repositories;

import com.project.inventorydistribution.DTOs.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    List<Role> findByRoleName(String roleName);
}
