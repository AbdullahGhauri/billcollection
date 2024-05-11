package com.project.inventorydistribution.Repositories;

import com.project.inventorydistribution.DTOs.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
