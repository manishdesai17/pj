package com.hackathon.HackathonManagement.repository;

import com.hackathon.HackathonManagement.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
    Registration findByEmail(String email);
}


