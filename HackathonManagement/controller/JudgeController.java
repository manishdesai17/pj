/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hackathon.HackathonManagement.controller;

//import com.hackathon.HackathonManagement.model.WinTeams;
import com.hackathon.HackathonManagement.service.JudgeServices;
//import com.hackathon.HackathonManagement.service.WinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Infinity
 */
@RestController
@RequestMapping("/judge")
public class JudgeController {

    @Autowired
    private JudgeServices judgeService;
//
//    @Autowired
//    private WinService winnerService;
    //    judge can select teams for 
    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<?> judgeConfirmTeam(@PathVariable("id") int id) {
        boolean result = this.judgeService.updateStatusById("selected", id);
        if (result) {
            return ResponseEntity.ok("Successfully Selected");
        }
        return ResponseEntity.ok("Something went problem not update status");
    }
    
     @PutMapping("/CancleSelection/{id}")
    public ResponseEntity<?> judgeCancle(@PathVariable("id") int id) {
        boolean result = this.judgeService.updateStatusById("pending", id);
        if (result) {
            return ResponseEntity.ok("Successfully Rejected");
        }
        return ResponseEntity.ok("Something went problem not update status");
    }

//    select winner team
//    @PostMapping("/selectWinTeam/{teamId}")
//    public ResponseEntity<?> judgeSelectWinTeams(@RequestBody WinTeams winTeams, @PathVariable("teamId") Long teamId) {
//        boolean result = this.judgeService.selectWinTeam(winTeams, teamId);
//        if (result) {
//            return ResponseEntity.ok("Successfully Selecte winner team");
//        }
//        return ResponseEntity.ok("Something went problem for select winner teams");
//    }
//    @PostMapping("/selectTeamForWin")
//    public ResponseEntity<WinTeams> addWinner(@RequestBody WinTeams winner) {
//        return ResponseEntity.ok(winnerService.saveWinner(winner));
//    }
}
