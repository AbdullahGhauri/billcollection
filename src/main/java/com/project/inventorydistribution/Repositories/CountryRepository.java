package com.project.inventorydistribution.Repositories;

import com.project.inventorydistribution.DTOs.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
}
