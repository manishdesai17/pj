/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hackathon.HackathonManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Infinity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeamLoginDto {
    private String email;
    private String password;
    private String role;

    // getters & setters
}

