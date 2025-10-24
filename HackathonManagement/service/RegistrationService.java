/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hackathon.HackathonManagement.service;


/**
 *
 * @author Infinity
 */

import com.hackathon.HackathonManagement.dto.RegistrationDto;
import com.hackathon.HackathonManagement.model.Teams;
import com.hackathon.HackathonManagement.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    public List<RegistrationDto> getAllRegistrations() {
        return registrationRepository.findAll().stream()
                .map(r -> new RegistrationDto(
                        r.getRegistration_id(),
                        r.getLeader_name(),
                        r.getEmail(),
                        r.getCity(),
                        r.getCourse(),
                        r.getStatus(),
                        r.getTeams() != null
                                ? r.getTeams().stream().map(Teams::getTeam_name).toList()
                                : null
                ))
                .toList();
    }

    public RegistrationDto getRegistrationById(Integer id) {
        return registrationRepository.findById(id)
                .map(r -> new RegistrationDto(
                        r.getRegistration_id(),
                        r.getLeader_name(),
                        r.getEmail(),
                        r.getCity(),
                        r.getCourse(),
                        r.getStatus(),
                        r.getTeams() != null
                                ? r.getTeams().stream().map(Teams::getTeam_name).toList()
                                : null
                ))
                .orElse(null);
    }
}


