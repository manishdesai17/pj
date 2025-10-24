/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hackathon.HackathonManagement.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Infinity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hackathon {

    private int h_id;
    
    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    @NotBlank
    @Size(min = 10, max = 5000)
    private String description;

    @NotBlank
    private String startDate;

    @NotNull
    private String endDate; // Use String if coming from HTML

    @NotBlank
    private String location;

    @NotBlank
    private String organizerName;

    @NotNull
    private String registrationDeadline;

    @Min(1)
    private int maxParticipants;

    @NotBlank
    @Size(min = 5, max = 5000)
    private String rules;

    @NotBlank
    @Size(min = 3, max = 1000)
    private String prizeDetails;

    @NotNull
    private String hackathonDate;

    private String image;

    private String HackathonMode;

    private String Leader_email;

    // Getters and Setters
}
