package com.project.inventorydistribution.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private String status;
    private String message;

    public ErrorResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
    public ErrorResponse(String message) {
        this.message = message;
    }

}
