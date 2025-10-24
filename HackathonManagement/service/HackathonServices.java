/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hackathon.HackathonManagement.service;

import com.hackathon.HackathonManagement.dto.Hackathon;
import com.hackathon.HackathonManagement.model.Hackathons;
import com.hackathon.HackathonManagement.repository.HackathonRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
/**
 *
 * @author Infinity
 */
@Component
public class HackathonServices {

    @Autowired
    private HackathonRepository hackathonRepository;
//    add hackathon method with image name and store image in folder

    public Hackathons createHackathon(Hackathons hackathon, MultipartFile imageFile) throws IOException {
        if (imageFile == null || imageFile.isEmpty()) {
            throw new IllegalArgumentException("Image file cannot be empty");
        }

        if (!imageFile.getContentType().startsWith("image/")) {
            throw new IllegalArgumentException("Only image files are allowed");
        }

        // Save to static/images folder
        String uploadDir = "src/main/resources/static/images/";
        Files.createDirectories(Paths.get(uploadDir));

        String fileName = imageFile.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);

        // Write the file
        Files.write(filePath, imageFile.getBytes());

        // Store relative path (so you can access it via browser)
        hackathon.setHackathon_img(fileName);

        return hackathonRepository.save(hackathon);
    }

//    get all hackathon
    public List<Hackathon> getAllHackathons() {
        return hackathonRepository.findAll().stream()
                .map(h -> new Hackathon(
                h.getId(),
                h.getName(),
                h.getDescription(),
                h.getStartDate().toString(), // Assuming it's LocalDate
                h.getEndDate().toString(),
                h.getLocation(),
                h.getOrganizerName(),
                h.getRegistrationDeadline().toString(),
                h.getMaxParticipants(),
                h.getRules(),
                h.getPrizeDetails(),
                h.getHackathonDate().toString(),
                h.getHackathon_img(), // image is not fetched when getting hackathon list
                h.getHackathonMode(),
                h.getLeader_email()
        ))
                .toList();
    }

    public Hackathon getHackathon(int id) {
        Hackathons h = hackathonRepository.findById(id).orElse(null);
        if (h == null) {
            return null;
        }

        return new Hackathon(
                h.getId(),
                h.getName(),
                h.getDescription(),
                h.getStartDate().toString(),
                h.getEndDate().toString(),
                h.getLocation(),
                h.getOrganizerName(),
                h.getRegistrationDeadline().toString(),
                h.getMaxParticipants(),
                h.getRules(),
                h.getPrizeDetails(),
                h.getHackathonDate().toString(),
                h.getHackathon_img(),
                h.getHackathonMode(),
                h.getLeader_email()
        );

    }
//    Remove hackathon

    public boolean removeHackathon(int hackathon_id) {
        Optional<Hackathons> hackathons = this.hackathonRepository.findById(hackathon_id);
        if (hackathons.isPresent()) {
            this.hackathonRepository.deleteById(hackathon_id);
            return true;
        }
        return false;
    }

//    Update hackathon
//    public boolean updateHackathon(Hackathons hackathon) {
//        this.hackathonRepository.save(hackathon);
//        return true;
//    }
    public boolean updateHackathon(Hackathons hackathon, MultipartFile imageFile) throws IOException {
        String uploadDir = "src/main/resources/static/images/";

        if (imageFile == null || imageFile.isEmpty()) {
            throw new IllegalArgumentException("Image file cannot be empty");
        }

        if (!imageFile.getContentType().startsWith("image/")) {
            throw new IllegalArgumentException("Only image files are allowed");
        }

        Hackathons existing = hackathonRepository.findById(hackathon.getId())
                .orElseThrow(() -> new IllegalArgumentException("Hackathon not found with ID: " + hackathon.getId()));

        String oldImageName = existing.getHackathon_img();
        if (oldImageName != null) {
            Path oldImagePath = Paths.get(uploadDir, oldImageName);
            Files.deleteIfExists(oldImagePath); // delete old image file
        }
        Files.createDirectories(Paths.get(uploadDir));

        String fileName = imageFile.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);

        // Write the file
        Files.write(filePath, imageFile.getBytes());

        // Store relative path (so you can access it via browser)
        hackathon.setHackathon_img(fileName);

        this.hackathonRepository.save(hackathon);
        return true;
    }

}
