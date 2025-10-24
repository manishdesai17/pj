/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hackathon.HackathonManagement.dto;


/**
 *
 * @author Infinity
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamWithMembersDto {
    private int team_id;
    private String team_name;
    private int team_size;
    private String driveLink;
    private String collageUnivercityName;
    private int hackathonId;
    private List<MemberDto> teamMemberName;

}
