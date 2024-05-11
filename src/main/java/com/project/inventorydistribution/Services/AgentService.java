package com.project.inventorydistribution.Services;

import com.project.inventorydistribution.Configuration.Jwtutil;
import com.project.inventorydistribution.DTOs.Agent;
import com.project.inventorydistribution.DTOs.JWTRequest;
import com.project.inventorydistribution.DTOs.JWTResponse;
import com.project.inventorydistribution.Exceptions.AlreadExistObjectException;
import com.project.inventorydistribution.Repositories.AgentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgentService {

    @Autowired
    AgentRepository agentRepository;

    @Autowired
    Jwtutil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    public Agent getAgentFromRepoById(long agentId){
        return agentRepository.findById(agentId).orElse(null);
    }


    public ResponseEntity<JWTResponse> register(Agent agent){
        Agent repoAgent = getAgentFromRepoById(agent.getAgentId());
        if(repoAgent == null){
            var user =  agentRepository.save(agent);
            var jwtToken = jwtTokenUtil.generateToken(user);
            return ResponseEntity.ok(new JWTResponse(jwtToken,getAgentFromRepoById(user.getAgentId())));
        }
        else{
            throw new AlreadExistObjectException("User already Exists");
        }
    }


    public ResponseEntity<JWTResponse> login(JWTRequest jwtRequest) {

        try {
        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(jwtRequest.getUserId(), jwtRequest.getPassword()));
        System.out.println("here");
        }catch (Exception e){
            System.out.println(e.toString());
        }
        var user  = agentRepository
                .findByAgentUserId(jwtRequest.getUserId()).orElseThrow();
        System.out.println("user--> "+user.toString());
        var jwt = jwtTokenUtil.generateToken(user);

        return ResponseEntity.ok(new JWTResponse(jwt,getAgentFromRepoById(user.getAgentId())));
    }

    public ResponseEntity<Agent> getAgentById(long agentId) {
        return ResponseEntity.ok(getAgentFromRepoById(agentId));
    }

    public ResponseEntity<List<Agent>> getAllAgent() {
        return  ResponseEntity.ok(agentRepository.findAll());
    }
}
