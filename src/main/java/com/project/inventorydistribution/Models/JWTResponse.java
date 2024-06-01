package com.project.inventorydistribution.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.inventorydistribution.DTOs.Agent;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JWTResponse {
    
    
    private String jwtToken;
    private Agent agent;
    private ErrorResponse errorResponse;

    public JWTResponse(String jwtToken, Agent agent) {
        this.jwtToken = jwtToken;
        this.agent = agent;
    }

    public JWTResponse(Agent agent) {
        this.agent = agent;
    }
    public JWTResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }
}
