/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hackathon.HackathonManagement.service;

/**
 *
 * @author Infinity
 */
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

//    #selected Mail come request from sendPassMail
    public boolean sendEmailWithPdf(String toEmail, String studentName, String teamName, byte[] pdfData) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(toEmail);
        helper.setSubject("Your Hackathon Pass");
        helper.setText("Hi " + studentName + ",\n\nAttached is your Hackathon pass. Best of luck!");
        helper.addAttachment("Hackathon_Pass.pdf", new ByteArrayResource(pdfData));
        mailSender.send(message);
        return true;
    }

//    Rejected mail come request from sendPassMail
    public boolean sendRejectedMail(String toEmail, String studentName, String teamName) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(toEmail);
        helper.setSubject("Dear " + studentName);
        helper.setText("Hi " + studentName + ",\n\n" + "Your team name is " + teamName + ",\n\n" + "Thank you for your interest in participating in the hackathon.After reviewing your registration, we regret to inform you that your application has been rejected due to issues found in your submitted documentation.");
        mailSender.send(message);
        return true;
    }

    public boolean sendCertificateEmail(String toEmail, String Name, String hackathonName, byte[] pdfData) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(toEmail);
        helper.setSubject("Your Hackathon Certificate");
        helper.setText("Hi " + Name + ",\n\nCongratulations!\n\nWe are excited to share your official certificate of participation in the " + hackathonName + "Your passion, creativity, and teamwork were truly appreciated throughout the event.\n\nWe wish you the very best in your future endeavors and hope to see you again in upcoming hackathons!\n\nWarm regards,  \n"
                + "\n\nHackathon Organizing Team \n\n"
                + hackathonName
                );
        helper.addAttachment("Hackathon_Certificate.pdf", new ByteArrayResource(pdfData));
        mailSender.send(message);
        return true;
    }
}

