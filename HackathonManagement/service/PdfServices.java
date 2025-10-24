/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hackathon.HackathonManagement.service;

/**
 *
 * @author Infinity
 */
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import java.io.*;
import java.nio.file.*;
import org.springframework.stereotype.Component;

@Component
public class PdfServices {

//    pass html design
    public byte[] generatePdf(String studentName, String teamName) throws IOException {
        // Load HTML template
        String html = Files.readString(Paths.get("C:/project2025/HackathonManagement/src/main/resources/templates/pass.html"));

        // Replace placeholders
        html = html.replace("{{name}}", studentName)
                .replace("{{team_name}}", teamName);

        // Convert to PDF
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.useFastMode();
        builder.useDefaultPageSize(297, 210, PdfRendererBuilder.PageSizeUnits.MM); // A4 landscape
        builder.withHtmlContent(html, null);
        builder.toStream(outputStream);
        builder.run();

        return outputStream.toByteArray();
    }

    public byte[] generatePdfCertificate(String studentName, String hackathonName, String stampName, String signName) throws IOException {
        String html = Files.readString(Paths.get("C:/project2025/HackathonManagement/src/main/resources/templates/certificate.html"));
        String signaturePath1 = new File("src/main/resources/static/sign/" + signName).toURI().toString();
        String signaturePath2 = new File("src/main/resources/static/stamp/" + stampName).toURI().toString();

        html = html.replace("{{student_name}}", studentName) 
                .replace("{{hackathonName}}", hackathonName)
                .replace("{{signature_path}}", signaturePath1)
                .replace("{{stamp_path}}", signaturePath2);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfRendererBuilder builder = new PdfRendererBuilder();

        builder.useFastMode();
        builder.useDefaultPageSize(297, 210, PdfRendererBuilder.PageSizeUnits.MM); // A4 landscape
        builder.withHtmlContent(html, null);
        builder.toStream(outputStream);
        builder.run();

        return outputStream.toByteArray();
    }
}
