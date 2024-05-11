package com.project.inventorydistribution.Repositories;

import com.project.inventorydistribution.DTOs.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
}
