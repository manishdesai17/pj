package com.hackathon.HackathonManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "team_member_detail")
public class TeamMemberDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int member_id;

    @NotBlank
    @Size(min = 2, max = 100)
    private String member_name;

    @NotBlank
    @Size(min = 2, max = 100)
    private String course;

    @NotBlank
    @Size(min = 2, max = 100)
    private String city;

    @NotBlank
    @Size(min = 2, max = 100)
    private String email;

    @NotBlank
    @Size(min = 2, max = 100)
    private String status;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Teams teams;

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Teams getTeams() {
        return teams;
    }

    public void setTeams(Teams teams) {
        this.teams = teams;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
