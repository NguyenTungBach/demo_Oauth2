package com.example.demo_auth02.entity;

import com.example.demo_auth02.entity.dto.Credential;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="authorizations")
public class Authorization {
    @Id
    private String code;
    private String scope;
    private long userId;

    private long clientId;
//    //1. Mối quan hệ giữa Authorization và Credential
//    @OneToOne
//    @JoinColumn(name = "credentialId",referencedColumnName = "id") // Khóa ngoại Customer
//    private Credential credential;
//    @Column(updatable = false,insertable = false)
//    private long credentialId;
}
