package com.project.inventorydistribution.Controllers;


import com.project.inventorydistribution.Configuration.Jwtutil;
import com.project.inventorydistribution.DTOs.Agent;
import com.project.inventorydistribution.DTOs.JWTRequest;
import com.project.inventorydistribution.DTOs.JWTResponse;
import com.project.inventorydistribution.Repositories.AgentRepository;
import com.project.inventorydistribution.Services.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/agent")
public class AgentController {

    @Autowired
    AgentService agentService;

    @GetMapping(value = "/getAgentById/{agentId}")
    public ResponseEntity<Agent> getAgentById(@PathVariable long agentId) throws Exception{
        return agentService.getAgentById(agentId);
    }
    @GetMapping(value = "/getAllAgent")
    public ResponseEntity<List<Agent>> getAllAgent() throws Exception{
        return agentService.getAllAgent();
    }
}
