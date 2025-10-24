package com.hackathon.HackathonManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "participant")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer participant_id;

    @ManyToOne
    @JoinColumn(name = "hackathon_id")
    @NotNull
    private Hackathons hackathon;

    @ManyToOne
    @JoinColumn(name = "registration_id")
    @NotNull
    private Registration registration;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Teams team;

    public Integer getParticipant_id() { return participant_id; }
    public void setParticipant_id(Integer participant_id) { this.participant_id = participant_id; }
    public Hackathons getHackathon() { return hackathon; }
    public void setHackathon(Hackathons hackathon) { this.hackathon = hackathon; }
    public Registration getRegistration() { return registration; }
    public void setRegistration(Registration registration) { this.registration = registration; }
    public Teams getTeam() { return team; }
    public void setTeam(Teams team) { this.team = team; }
}


