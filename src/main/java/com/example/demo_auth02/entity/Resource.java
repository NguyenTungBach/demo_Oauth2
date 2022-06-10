package com.example.demo_auth02.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="resources")
public class Resource {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String link;
    private String scope;
}
