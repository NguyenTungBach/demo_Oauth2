package com.example.demo_auth02.entity.dto;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
//@Entity
//@Table(name="credentials")
public class Credential {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
    private String accessToken;
//    private long userId;
//    private long clientId;
//    private String scope;

//    //1. Mối quan hệ giữa Credential và Authorization
//    @OneToOne
//    @JoinColumn(name = "authorizationCode",referencedColumnName = "code") // Khóa ngoại Customer
//    private Authorization authorization;
//    @Column(updatable = false,insertable = false)
//    private String authorizationCode;
}
