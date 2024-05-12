package com.project.inventorydistribution.Services;

import com.project.inventorydistribution.Configuration.Jwtutil;
import com.project.inventorydistribution.DTOs.Agent;
import com.project.inventorydistribution.DTOs.ErrorResponse;
import com.project.inventorydistribution.DTOs.JWTRequest;
import com.project.inventorydistribution.DTOs.JWTResponse;
import com.project.inventorydistribution.Exceptions.AlreadExistObjectException;
import com.project.inventorydistribution.Repositories.AgentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgentService {

    @Autowired
    AgentRepository agentRepository;

    @Autowired
    Jwtutil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    final String AGENT_NOT_FOUND = "Agent not found";
    final String INVALID_CREDENTIAL = "Invalid credentials";
    final String USER_ALREADY_EXISTS = "User ID already exists";

    public Agent getAgentFromRepoById(long agentId){
        return agentRepository.findById(agentId).orElse(null);
    }


    public ResponseEntity<JWTResponse> register(Agent agent){
        try{
            agent.setAgentPassword(passwordEncoder.encode(agent.getPassword()));
            agent.setStatus(true);
            var user =  agentRepository.save(agent);
            var jwtToken = jwtTokenUtil.generateToken(user);
            return ResponseEntity.ok(new JWTResponse(jwtToken,getAgentFromRepoById(user.getAgentId())));
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.ok(new JWTResponse(new ErrorResponse(USER_ALREADY_EXISTS)));
        }
    }



    public ResponseEntity<JWTResponse> login(JWTRequest jwtRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUserId(), jwtRequest.getPassword()));
            var user = agentRepository.findByAgentUserId(jwtRequest.getUserId()).orElse(null);
            if(user == null){
                return ResponseEntity.ok(new JWTResponse(new ErrorResponse(AGENT_NOT_FOUND)));
            }
            else if (passwordEncoder.matches(jwtRequest.getPassword(), user.getAgentPassword())) {
                var jwt = jwtTokenUtil.generateToken(user);
                return ResponseEntity.ok(new JWTResponse(jwt, getAgentFromRepoById(user.getAgentId())));
            } else {
                return ResponseEntity.ok(new JWTResponse(new ErrorResponse(INVALID_CREDENTIAL)));
            }
        } catch (AuthenticationException e) {
            return ResponseEntity.ok(new JWTResponse(new ErrorResponse(INVALID_CREDENTIAL)));
        } catch (NotFoundException e) {
            return ResponseEntity.ok(new JWTResponse(new ErrorResponse(AGENT_NOT_FOUND)));
        }
    }

    public ResponseEntity<Agent> getAgentById(long agentId) {
        return ResponseEntity.ok(getAgentFromRepoById(agentId));
    }

    public ResponseEntity<List<Agent>> getAllAgent() {
        return  ResponseEntity.ok(agentRepository.findByStatusTrue());
    }

    public ResponseEntity<Object> updateAgentService(long agentId, Agent agent) {
        Agent repoAgent = getAgentFromRepoById(agentId);
        System.out.println("repoAgent-->"+repoAgent);
        if(repoAgent == null){
            return ResponseEntity.ok(new ErrorResponse("Invalid Agent"));
        }
        else{
            repoAgent = agent;
            Agent respAgent = agentRepository.save(repoAgent);
            return ResponseEntity.ok(new JWTResponse(respAgent));
        }
    }
}
