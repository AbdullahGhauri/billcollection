package com.project.inventorydistribution.Models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class JWTRequest {
    private String userId;
    private String password;
}
