package com.project.inventorydistribution.Repositories;

import com.project.inventorydistribution.DTOs.SubScreen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubScreenRepository extends JpaRepository<SubScreen,Long> {
}
