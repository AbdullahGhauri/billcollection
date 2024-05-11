package com.project.inventorydistribution.DTOs;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class JWTResponse {
    private String jwtToken;
    private Agent agent;

}
