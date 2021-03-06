package com.example.demo_auth02.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username; // select * from account where username = "username"-> salt, passwordhash, passwordHash
    private String password; // đã mã hoá. salt+passwordhash (md5, sha)

}
