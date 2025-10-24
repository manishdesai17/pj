/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hackathon.HackathonManagement.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 *
 * @author Infinity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private String memberName;
    private String course;
    private String city ;
    private String email;
    private String status;
    private LocalDateTime timeDate; 
}
