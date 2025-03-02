package com.example.eLearningDyscalculiaDisability.repository;

import com.example.eLearningDyscalculiaDisability.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {}