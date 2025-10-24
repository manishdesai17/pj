package com.hackathon.HackathonManagement.service;

import com.hackathon.HackathonManagement.model.Teams;
import com.hackathon.HackathonManagement.model.Registration;
import com.hackathon.HackathonManagement.model.User;
import com.hackathon.HackathonManagement.repository.TeamRepository;
import com.hackathon.HackathonManagement.repository.RegistrationRepository;
import com.hackathon.HackathonManagement.repository.UserRepository;
import com.hackathon.HackathonManagement.security.JwtUtil;
import com.hackathon.HackathonManagement.service.AdminServies;
import com.hackathon.HackathonManagement.service.JudgeServices;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private TeamRepository teamRepository; 
    @Autowired
    private RegistrationRepository userRepository;
    @Autowired
    private UserRepository adminUserRepository;

    @Autowired
    private AdminServies adminServies;

    @Autowired
    private JudgeServices judgeServices;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public String loginUser(String email, String password) {
        Registration user = userRepository.findByEmail(email);
        if (user != null && password.equals(user.getPassword())) {
            return jwtUtil.generateToken(user.getEmail(), "USER", user.getRegistration_id());
        }
        return null;
    }

    public String loginAdmin(String email, String password) {
        // Simple admin login using User entity
        User admin = adminUserRepository.findByEmail(email);
        if (admin != null && password.equals(admin.getPassword())) {
            return jwtUtil.generateToken(admin.getEmail(), "ADMIN", admin.getId());
        }
        return null;
    }

    public String loginJudge(String email, String password) {
        // Simple judge login using User entity
        User judge = adminUserRepository.findByEmail(email);
        if (judge != null && password.equals(judge.getPassword())) {
            return jwtUtil.generateToken(judge.getEmail(), "JUDGE", judge.getId());
        }
        return null;
    }
}
