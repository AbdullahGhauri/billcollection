package com.project.inventorydistribution.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.inventorydistribution.DTOs.Agent;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AgentResponse {

    private Agent agent;
    private List<Agent> agentList;
    private ErrorResponse errorResponse;

    public AgentResponse(Agent agent) {this.agent = agent;}
    public AgentResponse(List<Agent> agentList) {this.agentList = agentList;}
    public AgentResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }
}
