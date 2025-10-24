package com.hackathon.HackathonManagement.repository;

import com.hackathon.HackathonManagement.model.Participant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Integer> {
    @Query("SELECT p FROM Participant p WHERE p.registration.registration_id = :registrationId")
    List<Participant> findByRegistrationId(@Param("registrationId") Integer registrationId);

    @Query("SELECT p.team FROM Participant p WHERE p.hackathon.id = :hackathonId")
    List<com.hackathon.HackathonManagement.model.Teams> findTeamsByHackathonId(@Param("hackathonId") int hackathonId);
}


