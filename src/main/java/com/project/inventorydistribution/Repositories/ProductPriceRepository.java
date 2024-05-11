package com.project.inventorydistribution.Repositories;

import com.project.inventorydistribution.DTOs.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice,Long> {
}
