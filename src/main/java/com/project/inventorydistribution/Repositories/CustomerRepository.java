package com.project.inventorydistribution.Repositories;

import com.project.inventorydistribution.DTOs.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
