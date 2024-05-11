package com.project.inventorydistribution.Repositories;

import com.project.inventorydistribution.DTOs.ScreenTab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenTabRepository extends JpaRepository<ScreenTab,Long> {
}
