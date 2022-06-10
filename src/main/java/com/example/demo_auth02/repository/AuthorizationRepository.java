package com.example.demo_auth02.repository;

import com.example.demo_auth02.entity.Authorization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorizationRepository extends JpaRepository<Authorization,String> {
}
