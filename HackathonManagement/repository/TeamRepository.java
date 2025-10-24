/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hackathon.HackathonManagement.repository;

import com.hackathon.HackathonManagement.model.Teams;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Infinity
 */
@Repository
public interface TeamRepository extends JpaRepository<Teams, Integer> {

//    hackathon relation removed from Teams; use ParticipantRepository instead
    Optional<Teams> findById(Integer teamId);
    
    // ownership helpers (owner is Registration). Use JPQL to avoid property parsing issues with underscores
    @Query("SELECT t FROM Teams t WHERE t.owner.registration_id = :registrationId")
    List<Teams> findByOwnerRegistrationId(@Param("registrationId") Integer registrationId);

    @Query("SELECT COUNT(t) FROM Teams t WHERE t.owner.registration_id = :registrationId")
    int countByOwnerRegistrationId(@Param("registrationId") Integer registrationId);

    @Query("SELECT t FROM Teams t WHERE t.team_id = :teamId AND t.owner.registration_id = :registrationId")
    Optional<Teams> findByIdAndOwnerRegistrationId(@Param("teamId") Integer teamId, @Param("registrationId") Integer registrationId);
     
//   for selected teams via Participant (Teams no longer has hackathon)
@Query("SELECT DISTINCT p.team FROM Participant p JOIN p.team.teamMemberName m WHERE p.hackathon.id = :hackathonId AND m.status = 'selected'")
List<Teams> findSelectedTeamsByHackathonId(@Param("hackathonId") int hackathonId);

}
