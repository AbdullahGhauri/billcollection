package com.project.inventorydistribution.Services;

import com.project.inventorydistribution.Configuration.Jwtutil;
import com.project.inventorydistribution.DTOs.Agent;
import com.project.inventorydistribution.Models.ErrorResponse;
import com.project.inventorydistribution.DTOs.Role;
import com.project.inventorydistribution.Models.AgentResponse;
import com.project.inventorydistribution.Models.JWTRequest;
import com.project.inventorydistribution.Models.JWTResponse;
import com.project.inventorydistribution.Repositories.AgentRepository;
import com.project.inventorydistribution.Repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;

    final String AGENT_NOT_FOUND = "Agent not found";
    final String INVALID_CREDENTIAL = "Invalid credentials";
    final String USER_ALREADY_EXISTS = "User ID already exists";
    final String NO_RECORD_FOUND = "No record found";
    final String ACCOUNT_DISABLE = "Account disabled";
    final String INVALID_ROLE = "Invalid Role";

    public Agent getAgentFromRepoById(long agentId){
        return agentRepository.findById(agentId).orElse(null);
    }


//    INSERT INTO `inventorydistribution`.`role` (`role_id`, `role_name`) VALUES ('1', 'ADMIN');
//    INSERT INTO `inventorydistribution`.`role` (`role_id`, `role_name`) VALUES ('2', 'USER');
    public ResponseEntity<JWTResponse> register(Agent agent){
        try{
            agent.setAgentPassword(passwordEncoder.encode(agent.getPassword()));
            String roleName = agent.getRole().getRoleName();
            List<Role> agentRoles = roleRepository.findByRoleName(roleName);
            System.out.println("agentRole-->"+agentRoles);
            Role agentRole = new
                    Role();
            if(agentRoles.size()==0){
                if(roleName.equalsIgnoreCase("ADMIN")){
                    Role newRole = new Role("ADMIN");
                    agentRole = roleRepository.save(newRole);
                }else if(roleName.equalsIgnoreCase("AGENT")){
                    Role newRole = new Role("AGENT");
                    agentRole = roleRepository.save(newRole);
                }else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JWTResponse(new ErrorResponse(INVALID_ROLE)));
                }
            }
            else{
                agentRole = agentRoles.get(0);
            }
            agent.setRole(agentRole);
            agent.setStatus(true);
            var user =  agentRepository.save(agent);
            var jwtToken = jwtTokenUtil.generateToken(user);
            return ResponseEntity.ok(new JWTResponse(jwtToken,getAgentFromRepoById(user.getAgentId())));
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JWTResponse(new ErrorResponse(USER_ALREADY_EXISTS)));
        }
    }



    public ResponseEntity<JWTResponse> login(JWTRequest jwtRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUserId(), jwtRequest.getPassword()));
            var user = agentRepository.findByAgentUserId(jwtRequest.getUserId()).orElse(null);
            if(user == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JWTResponse(new ErrorResponse(AGENT_NOT_FOUND)));
            }
            else if (passwordEncoder.matches(jwtRequest.getPassword(), user.getAgentPassword())) {
                var jwt = jwtTokenUtil.generateToken(user);
                if(user.isStatus()){
                    return ResponseEntity.ok(new JWTResponse(jwt, getAgentFromRepoById(user.getAgentId())));
                }
                else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JWTResponse(new ErrorResponse(ACCOUNT_DISABLE)));
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JWTResponse(new ErrorResponse(INVALID_CREDENTIAL)));
            }
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JWTResponse(new ErrorResponse(INVALID_CREDENTIAL)));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JWTResponse(new ErrorResponse(AGENT_NOT_FOUND)));
        }
    }

    public ResponseEntity<AgentResponse> getAgentById(long agentId) {
        Agent agent = getAgentFromRepoById(agentId);
        if(agent == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AgentResponse(new ErrorResponse(AGENT_NOT_FOUND)));
        }
        return ResponseEntity.ok(new AgentResponse(agent));
    }

    public ResponseEntity<AgentResponse> getAllAgent() {
        List<Agent> agentList = agentRepository.findAll();
        if(agentList.size() == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AgentResponse(new ErrorResponse(NO_RECORD_FOUND)));
        }
        return ResponseEntity.ok(new AgentResponse(agentList));
    }

    public ResponseEntity<AgentResponse> updateAgentService(long agentId, Agent agent) {
        Agent repoAgent = getAgentFromRepoById(agentId);
        if(repoAgent == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AgentResponse(new ErrorResponse(AGENT_NOT_FOUND)));
        }

        else{
            if (agent != null) {
                repoAgent.setAgentName(agent.getAgentName());
                repoAgent.setAgentUserId(agent.getAgentUserId());
                if(agent.getPassword() != null){
                    repoAgent.setAgentPassword(passwordEncoder.encode(agent.getPassword()));
                }
                repoAgent.setAgentContactNo(agent.getAgentContactNo());
                repoAgent.setAgentCnic(agent.getAgentCnic());
                repoAgent.setAgentEmail(agent.getAgentEmail());
                repoAgent.setStatus(agent.isStatus());
                repoAgent.setAddress(agent.getAddress());
                repoAgent.setRole(agent.getRole());
            }
            try{

                Agent respAgent = agentRepository.save(repoAgent);
                return ResponseEntity.ok(new AgentResponse(respAgent));
            } catch (DataIntegrityViolationException ex) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AgentResponse(new ErrorResponse(USER_ALREADY_EXISTS)));
            }
        }
    }
}
