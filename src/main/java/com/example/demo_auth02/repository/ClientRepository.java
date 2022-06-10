package com.example.demo_auth02.repository;

import com.example.demo_auth02.entity.Client;
import com.example.demo_auth02.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
}
