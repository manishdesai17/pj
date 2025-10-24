/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hackathon.HackathonManagement.controller;

import com.hackathon.HackathonManagement.dto.TeamWithMembersDto;
import com.hackathon.HackathonManagement.model.Teams;
import com.hackathon.HackathonManagement.security.JwtUtil;
import com.hackathon.HackathonManagement.service.TeamServices;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Infinity
 */
@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamServices teamServices;

    @Autowired
    private JwtUtil jwtUtil;
    // New: create team for current user (no hackathon binding yet)
    @PostMapping("/create")
    public ResponseEntity<?> createTeam(@Valid @RequestBody Teams teams, BindingResult bindingResult, @RequestHeader("Authorization") String authHeader) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        String token = authHeader.replace("Bearer ", "");
        Integer userId = jwtUtil.extractUserId(token);
        Teams created = teamServices.createTeamForUser(userId, teams);
        return ResponseEntity.ok(created.getTeam_id());
    }

//    get all teams for see team leader their team
    @GetMapping("/getAllTeams")
    public ResponseEntity<List<TeamWithMembersDto>> getAllTeams() {
        return ResponseEntity.ok(this.teamServices.getAllTeams());
    }

    // New: list current user's teams
    @GetMapping("/my")
    public ResponseEntity<List<TeamWithMembersDto>> getMyTeams(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        Integer userId = jwtUtil.extractUserId(token);
        return ResponseEntity.ok(teamServices.getTeamsByOwner(userId));
    }

//    delete team
    @DeleteMapping("/removeTeam/{team_id}")
    public ResponseEntity<?> removeTeams(@PathVariable("team_id") Integer team_id) {
        boolean result = this.teamServices.removeTeams(team_id);
        if (result) {
            return ResponseEntity.ok("Successfully team deleted.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found Team");
    }

    // New: delete team with ownership check
    @DeleteMapping("/my/{team_id}")
    public ResponseEntity<?> deleteMyTeam(@RequestHeader("Authorization") String authHeader,
                                          @PathVariable("team_id") Integer teamId) {
        String token = authHeader.replace("Bearer ", "");
        Integer userId = jwtUtil.extractUserId(token);
        teamServices.deleteTeamOwnedByUser(userId, teamId);
        return ResponseEntity.ok("Deleted");
    }

//    for update team
    @PutMapping("/updateTeam/{hackathon_id}")
    public ResponseEntity<?> updateTeam(@Valid @RequestBody Teams teams, BindingResult bindingResult, @PathVariable("hackathon_id") int hackthon_id) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
                System.out.println(error.getField() + " " + error.getDefaultMessage());
            }
            return ResponseEntity
                    .badRequest()
                    .body(errors);
        }
        boolean result = this.teamServices.updateTeam(teams, hackthon_id);
        if (result) {
            return ResponseEntity.ok("Successfully update record.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Something went wrong not update team record.");
    }

//    value get using hackathon id for admin can see click on hackathon name
    @GetMapping("/getTeamsByHackathon/{hackathonId}")
    public ResponseEntity<List<TeamWithMembersDto>> getTeamsByHackathon(@PathVariable int hackathonId) {
        return ResponseEntity.ok(teamServices.getTeamsByHackathonId(hackathonId));
    }

//    get team by team id
    @GetMapping("/getTeamById/{teamId}")
    public ResponseEntity<TeamWithMembersDto> getTeamById(@PathVariable int teamId) {
        return ResponseEntity.ok(teamServices.getTeamById(teamId));
    }

//    @GetMapping("/getTeamIdsByHackathon/{hackathonId}")
//    public ResponseEntity<List<Integer>> getTeamIdsByHackathon(@PathVariable int hackathonId) {
//        return ResponseEntity.ok(teamServices.getTeamIdsByHackathon(hackathonId));
//    }
    @GetMapping("/profile")
    public ResponseEntity<TeamWithMembersDto> getProfile(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        Integer userId = jwtUtil.extractUserId(token);

        List<TeamWithMembersDto> teams = teamServices.getTeamsByOwner(userId);
        return ResponseEntity.ok(teams.stream().findFirst().orElse(null));
    }

    // New: select an existing team for a hackathon registration
    @PostMapping("/select/{hackathon_id}/{team_id}")
    public ResponseEntity<?> selectTeamForHackathon(@RequestHeader("Authorization") String authHeader,
                                                    @PathVariable("hackathon_id") Integer hackathonId,
                                                    @PathVariable("team_id") Integer teamId) {
        String token = authHeader.replace("Bearer ", "");
        Integer userId = jwtUtil.extractUserId(token);
        teamServices.registerTeamToHackathon(userId, teamId, hackathonId);
        return ResponseEntity.ok("Team registered to hackathon");
    }

}
