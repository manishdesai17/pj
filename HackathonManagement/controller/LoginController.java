package com.hackathon.HackathonManagement.controller;

import com.hackathon.HackathonManagement.dto.TeamLoginDto;
import com.hackathon.HackathonManagement.model.Teams;
import com.hackathon.HackathonManagement.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public String login(@RequestBody TeamLoginDto team) {
        System.out.println("data=" + team);
        String role=team.getRole();
   
        String token;
        switch (role.toUpperCase()) {
            case "ADMIN":
                token = loginService.loginAdmin(team.getEmail(), team.getPassword());
                break;
            case "JUDGE":
                token = loginService.loginJudge(team.getEmail(), team.getPassword());
                break;
            case "USER":
            default:
                token = loginService.loginUser(team.getEmail(), team.getPassword());
                break;
        }
        return (token != null) ? token : "INVALID_CREDENTIALS";
    }
}
