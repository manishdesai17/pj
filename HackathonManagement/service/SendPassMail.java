/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hackathon.HackathonManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Infinity
 */
@Component
public class SendPassMail {

    @Autowired
    private PdfServices pdfService;

    @Autowired
    private EmailService emailService;

    String studentName = "";
    String teamName = "";
    String email = "";
    String stamp = "";
    String sign = "";
    String hackathon = "";

    public boolean sendCertificate(String studentName, String hackathonName, String stampName, String signName, String studentEmail) {
        this.studentName = studentName;
        this.hackathon = hackathonName;
        this.sign = signName;
        this.stamp = stampName;
        this.email = studentEmail;

        try {
            byte[] pdf = pdfService.generatePdfCertificate(studentName, hackathon, stamp, sign);
            boolean result = emailService.sendCertificateEmail(email, studentName, hackathon, pdf);
            if (result) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

//    This is confirm to selected in hackathon mail
    public boolean sendSelectedmail(String sname, String tname, String semail) {
        this.studentName = sname;
        this.teamName = tname;
        this.email = semail;
        try {

            byte[] pdf = pdfService.generatePdf(studentName, teamName);
            boolean result = emailService.sendEmailWithPdf(email, studentName, teamName, pdf);
            if (result) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

//    #this is rejected in hackathon mail
    public boolean sendRejectiondMail(String sname, String tname, String semail) {
        this.studentName = sname;
        this.teamName = tname;
        this.email = semail;
        try {
            boolean result = emailService.sendRejectedMail(email, studentName, teamName);
            if (result) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
