package com.project.inventorydistribution.Repositories;

import com.project.inventorydistribution.DTOs.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
}
