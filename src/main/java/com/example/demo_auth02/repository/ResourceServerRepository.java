package com.example.demo_auth02.repository;

import com.example.demo_auth02.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceServerRepository extends JpaRepository<Resource,Long> {
    List<Resource> findByScope(String Scope);
}
