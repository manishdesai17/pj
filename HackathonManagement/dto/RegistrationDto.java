/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hackathon.HackathonManagement.dto;

/**
 *
 * @author Infinity
 */

import java.util.List;

public class RegistrationDto {
    private Integer registrationId;
    private String leaderName;
    private String email;
    private String city;
    private String course;
    private String status;
    private List<String> teams; // just team names

    public RegistrationDto(Integer registrationId, String leaderName, String email,
                           String city, String course, String status, List<String> teams) {
        this.registrationId = registrationId;
        this.leaderName = leaderName;
        this.email = email;
        this.city = city;
        this.course = course;
        this.status = status;
        this.teams = teams;
    }

    // Getters
    public Integer getRegistrationId() { return registrationId; }
    public String getLeaderName() { return leaderName; }
    public String getEmail() { return email; }
    public String getCity() { return city; }
    public String getCourse() { return course; }
    public String getStatus() { return status; }
    public List<String> getTeams() { return teams; }
}
