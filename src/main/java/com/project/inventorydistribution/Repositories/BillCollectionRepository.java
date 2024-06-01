package com.project.inventorydistribution.Repositories;

import com.project.inventorydistribution.DTOs.BillCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillCollectionRepository extends JpaRepository<BillCollection,Long> {

}
