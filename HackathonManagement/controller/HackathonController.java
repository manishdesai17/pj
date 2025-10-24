/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hackathon.HackathonManagement.controller;

import com.hackathon.HackathonManagement.dto.Hackathon;
import com.hackathon.HackathonManagement.model.Hackathons;
import com.hackathon.HackathonManagement.service.HackathonServices;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Infinity
 */
@RestController
public class HackathonController {
//    add hackathon 

    @Autowired
    private HackathonServices hackathonServices;

//    add hackathon with image name and image store in folder images
    @PostMapping(value = "/addHackathonimage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createHackathon(@Valid 
            @RequestPart("hackathon") Hackathons hackathon,BindingResult bindingResult,@RequestPart("image") MultipartFile imageFile ) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> errors = new HashMap<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    errors.put(error.getField(), error.getDefaultMessage());
                }
                return ResponseEntity.badRequest().body(errors);
            }

            Hackathons savedHackathon = hackathonServices.createHackathon(hackathon, imageFile);
            return ResponseEntity.ok("Add record successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

//    get all hackathon
    @GetMapping("/getAllHackathon")
    public ResponseEntity<?> getAllHackathon() {
        List<Hackathon> hackathonses = this.hackathonServices.getAllHackathons();
        if (hackathonses.isEmpty()) {
            return ResponseEntity.ok("record not found");
        }
        return ResponseEntity.of(Optional.of(hackathonses));
    }

    
//    get hackathon by id
    @GetMapping("/getAllHackathonById/{id}")
    public ResponseEntity<?> getHackathonById(@PathVariable("id") int id) {
        Hackathon hackathonses = this.hackathonServices.getHackathon(id);
        return ResponseEntity.of(Optional.of(hackathonses));
    }
//    Delete hackathon
    @DeleteMapping("/removeHackathon/{hackathon_id}")
    public ResponseEntity<?> removeHackathon(@PathVariable("hackathon_id") int hackathon_id) {
        boolean result = this.hackathonServices.removeHackathon(hackathon_id);
        if (result) {
            return ResponseEntity.ok("Successfully deleted.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found Hachathon");
    }
    
//    Update hackathon with image
    @PutMapping(value = "/updateHackathon", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateHackathon(@Valid 
            @RequestPart("hackathon") Hackathons hackathon,BindingResult bindingResult,@RequestPart("image") MultipartFile imageFile ) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> errors = new HashMap<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    errors.put(error.getField(), error.getDefaultMessage());
                }
                return ResponseEntity.badRequest().body(errors);
            }

            boolean result = hackathonServices.updateHackathon(hackathon, imageFile);
            return ResponseEntity.ok("Successfully updated.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
