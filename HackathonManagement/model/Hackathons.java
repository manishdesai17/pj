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
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import lombok.*;

/**
 *
 * @author Infinity
 */
//Hackathon Table create here
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Hackathons {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    @Lob
    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 5000, message = "Description must be between 10 and 5000 characters")
    private String description;

    @NotBlank(message = "Start date is required")
    private String startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @NotBlank(message = "Location is required")
    @Size(min = 3, max = 255, message = "Location must be between 3 and 255 characters")
    private String location;

    @NotBlank(message = "Organizer name is required")
    @Size(min = 3, max = 255, message = "Organizer name must be between 3 and 255 characters")
    private String organizerName;

    @NotNull(message = "Registration deadline is required")
    private LocalDate registrationDeadline;

    @Min(value = 1, message = "There must be at least 1 participant")
    private int maxParticipants;

    @Lob
    @NotBlank(message = "Rules are required")
    @Size(min = 5, max = 5000, message = "Rules must be between 5 and 5000 characters")
    private String rules;

    @NotBlank(message = "Prize details are required")
    @Size(min = 3, max = 1000, message = "Prize details must be between 3 and 1000 characters")
    private String prizeDetails;

    private String hackathon_img;

    @NotNull(message = "Hackathon mode is required")
    private String hackathonMode;

    @NotNull(message = "Hackathon date is required")
    private LocalDate hackathonDate;

    @NotNull(message = "Email is required")
    private String leader_email;

    @NotNull(message = "password is required")
    private String leader_password;
    
    // relation to Teams removed; team-hackathon is tracked via Participant
}
