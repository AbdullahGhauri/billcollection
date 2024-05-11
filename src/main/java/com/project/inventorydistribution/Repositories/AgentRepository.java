package com.project.inventorydistribution.Repositories;

import com.project.inventorydistribution.DTOs.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<Agent,Long> {
    Optional<Agent> findByAgentUserId(String agentUserId);
}
