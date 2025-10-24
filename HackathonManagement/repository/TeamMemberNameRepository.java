/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hackathon.HackathonManagement.repository;

import com.hackathon.HackathonManagement.model.TeamMemberDetail;
import com.hackathon.HackathonManagement.model.Teams;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Infinity
 */
@Repository
public interface TeamMemberNameRepository extends JpaRepository<TeamMemberDetail, Integer> {

    
    
    
    List<TeamMemberDetail> findByStatus(String status);

    @Modifying
    @Transactional
    @Query("UPDATE TeamMemberDetail t SET t.status = :status WHERE t.teams.id = :teamId")
    void updateStatusByTeamId(@Param("status") String status, @Param("teamId") int teamId);

//    get teams selected in hackathon
    @Query("SELECT DISTINCT t.teams FROM TeamMemberDetail t WHERE t.status = :status")
    List<Teams> findTeamsByMemberStatus(@Param("status") String status);
}
