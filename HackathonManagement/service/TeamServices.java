/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hackathon.HackathonManagement.service;

import com.hackathon.HackathonManagement.dto.MemberDto;
import com.hackathon.HackathonManagement.dto.TeamWithMembersDto;
import com.hackathon.HackathonManagement.model.TeamMemberDetail;
import com.hackathon.HackathonManagement.model.Teams;
import com.hackathon.HackathonManagement.model.Registration;
import com.hackathon.HackathonManagement.repository.HackathonRepository;
import com.hackathon.HackathonManagement.repository.TeamMemberNameRepository;
import com.hackathon.HackathonManagement.repository.TeamRepository;
import com.hackathon.HackathonManagement.repository.RegistrationRepository;
import com.hackathon.HackathonManagement.repository.ParticipantRepository;
import com.hackathon.HackathonManagement.model.Participant;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Infinity
 */
@Service
public class TeamServices {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamMemberNameRepository TeamMemberNameRepository;

    @Autowired
    private HackathonRepository hackathonRepository;
    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private RegistrationRepository userRepository;

    // New: create a team for a user without hackathon association; enforce max 5
    public Teams createTeamForUser(Integer userId, Teams teams) {
        Registration owner = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        int count = teamRepository.countByOwnerRegistrationId(userId);
        if (count >= 5) {
            throw new IllegalStateException("Maximum 5 teams allowed per user");
        }
        teams.setOwner(owner);
        // no hackathon on team now
        if (teams.getTeamMemberName() != null) {
            for (TeamMemberDetail m : teams.getTeamMemberName()) {
                m.setTeams(teams);
            }
        }
        return teamRepository.save(teams);
    }

//    get all teams
    public List<TeamWithMembersDto> getAllTeams() {
        return teamRepository.findAll().stream()
                .map(team -> new TeamWithMembersDto(
                team.getTeam_id(),
                team.getTeam_name(),
                team.getTeam_size(),
                team.getDriveLink(),
                team.getCollageUnivercityName(),
                0,
                team.getTeamMemberName().stream()
                        .map(member -> new MemberDto(
                        member.getMember_name(),
                        member.getCourse(),
                        member.getCity(),
                        null,
                        member.getStatus(),
                        null
                ))
                        .toList()
        )).toList();
    }

    // List teams owned by a specific user
    public List<TeamWithMembersDto> getTeamsByOwner(Integer userId) {
        return teamRepository.findByOwnerRegistrationId(userId).stream()
                .map(team -> new TeamWithMembersDto(
                        team.getTeam_id(),
                        team.getTeam_name(),
                        team.getTeam_size(),
                        team.getDriveLink(),
                        team.getCollageUnivercityName(),
                        0,
                        team.getTeamMemberName().stream()
                                .map(member -> new MemberDto(
                                        member.getMember_name(),
                                        member.getCourse(),
                                        member.getCity(),
                                        null,
                                        member.getStatus(),
                                        null
                                ))
                                .toList()
                ))
                .toList();
    }

    // Register an existing user team to a hackathon (select a team)
    public void registerTeamToHackathon(Integer userId, Integer teamId, Integer hackathonId) {
        Teams team = teamRepository.findByIdAndOwnerRegistrationId(teamId, userId)
                .orElseThrow(() -> new RuntimeException("Team not found or not owned by user"));
        Participant participant = new Participant();
        participant.setHackathon(hackathonRepository.findById(hackathonId)
                .orElseThrow(() -> new RuntimeException("Hackathon not found")));
        participant.setRegistration(userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found")));
        participant.setTeam(team);
        participantRepository.save(participant);
    }

//    remove team
    public boolean removeTeams(Integer team_id) {
        Optional<Teams> teams = this.teamRepository.findById(team_id);
        if (teams.isPresent()) {
            this.teamRepository.deleteById(team_id);
            return true;
        }
        return false;
    }

    // Delete team owned by a user (ownership enforced)
    public void deleteTeamOwnedByUser(Integer userId, Integer teamId) {
        Teams team = teamRepository.findByIdAndOwnerRegistrationId(teamId, userId)
                .orElseThrow(() -> new RuntimeException("Team not found or not owned by user"));
        teamRepository.delete(team);
    }

    //for update team and teamMember both
    public boolean updateTeam(Teams teams, int hackathon_id) {
        if (teams.getTeamMemberName() != null) {
            for (TeamMemberDetail teamName : teams.getTeamMemberName()) {
                teamName.setTeams(teams);
            }
        }
        this.teamRepository.save(teams);
        return true;
    }

//    this is admin can see team acording by hackathon 
    public List<TeamWithMembersDto> getTeamsByHackathonId(int hackathonId) {
        return participantRepository.findTeamsByHackathonId(hackathonId)
                .stream()
                .map(team -> new TeamWithMembersDto(
                team.getTeam_id(),
                team.getTeam_name(),
                team.getTeam_size(),
                team.getDriveLink(),
                team.getCollageUnivercityName(),
                hackathonId,
                team.getTeamMemberName().stream()
                        .map(member -> new MemberDto(
                        member.getMember_name(),
                        member.getCourse(),
                        member.getCity(),
                        null,
                        member.getStatus(),
                        null
                ))
                        .toList()
        ))
                .toList();
    }

//    here come any problem so it come for i add hackahon _id get in dto so it come
//    i can change that in admin service controller 
//    get teams by team id
    public TeamWithMembersDto getTeamById(int teamId) {
        return teamRepository.findById(teamId)
                .map(team -> new TeamWithMembersDto(
                team.getTeam_id(),
                team.getTeam_name(),
                team.getTeam_size(),
                team.getDriveLink(),
                team.getCollageUnivercityName(),
                0,
                team.getTeamMemberName().stream()
                        .map(member -> new MemberDto(
                        member.getMember_name(),
                        member.getCourse(),
                        member.getCity(),
                        member.getEmail(),
                        member.getStatus(),
                        null
                ))
                        .toList()
        ))
                .orElseThrow(() -> new RuntimeException("Team not found with id: " + teamId));
    }

//    this is for winning team
//    public List<Integer> getTeamIdsByHackathon(int hackathonId) {
//    return teamRepository.findByHackathonId(hackathonId).stream()
//            .map(Teams::getTeam_id)   // extract only team_id
//            .toList();
//}
}
