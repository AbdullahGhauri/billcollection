package com.project.inventorydistribution.Repositories;

import com.project.inventorydistribution.DTOs.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<Province,Long> {
}
