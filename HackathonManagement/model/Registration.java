package com.hackathon.HackathonManagement.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "registration")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer registration_id;

    @NotBlank
    private String leader_name;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String city;

    @NotBlank
    private String course;

    @NotBlank
    private String status;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Teams> teams;

    public Integer getRegistration_id() { return registration_id; }
    public void setRegistration_id(Integer registration_id) { this.registration_id = registration_id; }
    public String getLeader_name() { return leader_name; }
    public void setLeader_name(String leader_name) { this.leader_name = leader_name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<Teams> getTeams() { return teams; }
    public void setTeams(List<Teams> teams) { this.teams = teams; }
}


