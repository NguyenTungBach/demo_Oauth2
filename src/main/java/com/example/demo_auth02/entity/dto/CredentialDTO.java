package com.example.demo_auth02.entity.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CredentialDTO {
    private long userId;
    private long clientId;
    private String scope;
}
