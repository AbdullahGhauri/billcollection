package com.project.inventorydistribution.Controllers;

import com.project.inventorydistribution.DTOs.Agent;
import com.project.inventorydistribution.Models.JWTRequest;
import com.project.inventorydistribution.Models.JWTResponse;
import com.project.inventorydistribution.Services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agentAuth")
@CrossOrigin(origins = "*")
public class AgentAuthController {
    @Autowired
    AgentService agentService;

    @PostMapping("/agentSignUp")
    public ResponseEntity<JWTResponse> agentSignUp(@RequestBody Agent agent){
        return agentService.register(agent);
    }

    @PostMapping(value = "/agentLogin")
    public ResponseEntity<JWTResponse> agentLogin(@RequestBody JWTRequest jwtRequest) throws Exception{
        return agentService.login(jwtRequest);
    }
}
