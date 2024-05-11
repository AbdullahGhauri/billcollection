package com.project.inventorydistribution.Repositories;

import com.project.inventorydistribution.DTOs.ProductSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSubCategoryRepository extends JpaRepository<ProductSubCategory,Long> {
}
