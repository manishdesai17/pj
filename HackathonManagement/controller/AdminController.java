/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hackathon.HackathonManagement.controller;

import com.hackathon.HackathonManagement.dto.TeamWithMembersDto;
import com.hackathon.HackathonManagement.model.Teams;
import com.hackathon.HackathonManagement.service.AdminServies;
import com.hackathon.HackathonManagement.service.SendPassMail;
import com.hackathon.HackathonManagement.service.TeamServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Infinity
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private TeamServices teamServices;

    @Autowired
    private AdminServies adminServies;
    //    get all teams teams and send mail selected and rejected both
    @GetMapping("/sendDisitionMail")
    public ResponseEntity<?> sendMail() {
        List<TeamWithMembersDto> list = this.teamServices.getAllTeams();
        boolean result = this.adminServies.SendSelectionAndRejectionMail(list);
        if (result) {
            return ResponseEntity.ok("Send mail successfully.");
        }
        return ResponseEntity.ok("Something problem not send email");
    }

    //    get all teams
    @GetMapping("/getAllTeams")
    public ResponseEntity<List<TeamWithMembersDto>> getAllTeams() {
        return ResponseEntity.ok(this.teamServices.getAllTeams());
    }

//    get selected teams in hackathon
    @GetMapping("/getSelectedTeams")
    public ResponseEntity<?> getAllSelectedTeams() {
        List<TeamWithMembersDto> result = this.adminServies.getSelectedTeams();
        return ResponseEntity.ok(result);
    }

//    this is for send participate certificate
    @GetMapping("/sendCertificate")
    public ResponseEntity<?> sendParticipateCertificate() {
        List<TeamWithMembersDto> list = this.teamServices.getAllTeams();
        boolean result = this.adminServies.sendParticipateCertificate(list);
        if (result) {
            return ResponseEntity.ok("Send Certificate successfully.");
        }
        return ResponseEntity.ok("Something problem not to send certificate");
    }
    
//    get teams match one member status selected and get teams details
  @GetMapping("/selected/{hackathonId}")
    public List<TeamWithMembersDto> getSelectedTeams(@PathVariable int hackathonId) {
        return this.adminServies.getSelectedTeams(hackathonId);
    }
    
//  @GetMapping("/winning-teams/{hackathonId}")
//  public List<TeamWithMembersDto> getWinningTeams(@PathVariable int hackathonId) {
//    return this.adminServies.getWinningTeams(hackathonId);
//  }
//    
     //    send pass using email system
//    @GetMapping("/send-pass")
//    public String sendPassToStudent() {
//        try {
//            boolean result = this.sendPassMail.sendPassMailSuceessfully();
//            if (result) {
//                return "Pass sent successfully!";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Failed to send pass.";
//        }
//         return "Failed to send pass.";
//    }
    
}
