package com.project.inventorydistribution.Controllers;


import com.project.inventorydistribution.DTOs.Agent;
import com.project.inventorydistribution.Models.AgentResponse;
import com.project.inventorydistribution.Services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/agent")
@CrossOrigin(origins = "*")
public class AgentController {

    @Autowired
    AgentService agentService;

    @GetMapping(value = "/getAgentById/{agentId}")
    public ResponseEntity<AgentResponse> getAgentById(@PathVariable long agentId) throws Exception{
        return agentService.getAgentById(agentId);
    }
    @GetMapping(value = "/getAllAgent")
    public ResponseEntity<AgentResponse> getAllAgent() throws Exception{
        return agentService.getAllAgent();
    }
    @PutMapping("/updateAgent/{agentId}")
    public ResponseEntity<AgentResponse> updateAgent(@PathVariable long agentId,@RequestBody Agent agent) throws Exception{
        return agentService.updateAgentService(agentId,agent);
    }
}
