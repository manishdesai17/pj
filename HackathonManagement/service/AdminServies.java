/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hackathon.HackathonManagement.service;

import com.hackathon.HackathonManagement.dto.MemberDto;
import com.hackathon.HackathonManagement.dto.TeamWithMembersDto;
import com.hackathon.HackathonManagement.model.Teams;
import com.hackathon.HackathonManagement.repository.TeamMemberNameRepository;
import com.hackathon.HackathonManagement.repository.TeamRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Infinity
 */
@Component
public class AdminServies {

    @Autowired
    private TeamRepository teamRepository;
    
    @Autowired
    private SendPassMail SendMail;

    @Autowired
    private TeamMemberNameRepository teamMemberNameRepository;

    List<TeamWithMembersDto> list;

//    Send mail selected and rejected both team member
    public boolean SendSelectionAndRejectionMail(List<TeamWithMembersDto> list) {
        this.list = list;
        for (TeamWithMembersDto team : this.list) {
            for (MemberDto memberDto : team.getTeamMemberName()) {
                String name = memberDto.getMemberName();
                String email = memberDto.getEmail();
                String status = memberDto.getStatus();
                String tname = team.getTeam_name();
                if (status.equals("selected")) {
                    boolean result = this.SendMail.sendSelectedmail(name, tname, email);
                } else if (status.equals("pending")) {
                    boolean result = this.SendMail.sendRejectiondMail(name, tname, email);
                }

            }
        }
        return true;
    }

//    get selected teams in hackathon
    public List<TeamWithMembersDto> getSelectedTeams() {
        List<Teams> teams = this.teamMemberNameRepository.findTeamsByMemberStatus("selected");

        return teams.stream()
                .map(team -> new TeamWithMembersDto(
                team.getTeam_id(),
                team.getTeam_name(),
                team.getTeam_size(),
                team.getDriveLink(),
                team.getCollageUnivercityName(),
                        0,
                team.getTeamMemberName().stream()
                        .filter(member -> member.getStatus().equals("selected")) // include only selected members
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

    public boolean sendParticipateCertificate(List teamMemberEmail) {
        this.list = teamMemberEmail;
        for (TeamWithMembersDto team : this.list) {
            for (MemberDto memberDto : team.getTeamMemberName()) {
                String name = memberDto.getMemberName();
                String email = memberDto.getEmail();
                String status = memberDto.getStatus();;
                if (status.equals("selected")) {
                    boolean result = this.SendMail.sendCertificate(name,   "Odoo Hackathon Gujarat Vidyapith", "stamps.png", "signature.png", email);
                    return true;
                }
            }
        }
        return false;
    }


//for get selected team by hackathon    
    public List<TeamWithMembersDto> getSelectedTeams(int hackathonId) {
    return this.teamRepository.findSelectedTeamsByHackathonId(hackathonId)
            .stream()
            .map(team -> new TeamWithMembersDto(
                    team.getTeam_id(),
                    team.getTeam_name(),
                    team.getTeam_size(),
                    team.getDriveLink(),
                    team.getCollageUnivercityName(),
                    hackathonId,
                    team.getTeamMemberName().stream()
                            .filter(member -> member.getStatus().equals("selected")) // only selected members
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

//    get win teams by hackathon id
//    public List<TeamWithMembersDto> getWinningTeams(int hackathonId) {
//    return teamRepository.findWinningTeamsByHackathonId(hackathonId)
//            .stream()
//            .map(team -> new TeamWithMembersDto(
//                    team.getTeam_id(),
//                    team.getTeam_name(),
//                    team.getTeam_size(),
//                    team.getDriveLink(),
//                    team.getCollageUnivercityName(),
//                    team.getTeamMemberName().stream()
//                        .map(member -> new MemberDto(
//                            member.getMemberName(),
//                            member.getCourse(),
//                            member.getCity(),
//                            member.getEmail(),
//                            member.getStatus(),
//                            member.getTimeDate()
//                        ))
//                        .toList()
//            ))
//            .toList();
//}

}
