/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hackathon.HackathonManagement.service;

//import com.hackathon.HackathonManagement.model.WinTeams;
import com.hackathon.HackathonManagement.repository.TeamMemberNameRepository;
//import com.hackathon.HackathonManagement.repository.WinTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Infinity
 */
@Service
public class JudgeServices {

    @Autowired
    private TeamMemberNameRepository teamMemberNameRepository;

//    @Autowired
//    private WinTeamRepository winTeamRepository;

//    update by team id
    public boolean updateStatusById(String status, int id) {
        try {
            this.teamMemberNameRepository.updateStatusByTeamId(status, id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//    Select winner team

}
