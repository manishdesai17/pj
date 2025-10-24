/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hackathon.HackathonManagement.controller;

import com.hackathon.HackathonManagement.dto.RegistrationDto;
import com.hackathon.HackathonManagement.model.Registration;
import com.hackathon.HackathonManagement.service.RegistrationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Infinity
 */
@RestController
@RequestMapping("/registration")
@CrossOrigin
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/all")
    public List<RegistrationDto> getAllRegistrations() {
        return registrationService.getAllRegistrations();
    }

    @GetMapping("/getUserDetail/{id}")
    public RegistrationDto getRegistrationById(@PathVariable Integer id) {
        return registrationService.getRegistrationById(id);
    }
}

