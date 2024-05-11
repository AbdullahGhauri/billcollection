package com.project.inventorydistribution.Repositories;

import com.project.inventorydistribution.DTOs.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
