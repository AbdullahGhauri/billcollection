package com.project.inventorydistribution.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
