/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hackathon.HackathonManagement.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Infinity
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int team_id;

    @NotBlank(message = "Team name is required")
    @Size(min = 2, max = 100, message = "Team name must be between 2 and 100 characters")
    private String team_name;

    @Min(value = 1, message = "Team size must be at least 1")
    private int team_size;

    private LocalDateTime timeDate;

//    @NotBlank(message = "driveLink is required")
    @jakarta.persistence.Column(name = "drive_link")
    private String driveLink;

//    @NotBlank(message = "collage/Univercity Name is required")
    @Size(min = 2, max = 100, message = "collage/Univercity name must be between 10 and 100 characters")
    @jakarta.persistence.Column(name = "univercity_collage_name")
    private String collageUnivercityName;

    // Owner is Registration now
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registration_id")
    private Registration owner;

    // Hackathon relation removed; selection is tracked in Participant table

    @OneToMany(mappedBy = "teams", cascade = CascadeType.ALL, orphanRemoval = true)
    @Valid
    private List<TeamMemberDetail> teamMemberName;

    @Override
    public String toString() {
        return "Teams [team_id=" + team_id + ", team_name=" + team_name + ", team_size=" + team_size + ", timeDate="
                + timeDate + ", driveLink=" + driveLink + ", collageUnivercityName=" + collageUnivercityName
                + ", owner=" + (owner != null ? owner.getRegistration_id() : null) + ", teamMemberName="
                + teamMemberName + "]";
    }


}
