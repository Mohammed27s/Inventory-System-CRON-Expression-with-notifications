package com.Inventory.System.notifications.TRA.Services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class MailingService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${email_secret}") //TODO change this token name and add it in enviorment virabiles
    private String senderEmail;

    // Method to send a simple text email
    public String sendSimpleMail() {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo("taibaalmujaini@gmail.com");
            mailMessage.setText("Hi");
            mailMessage.setSubject("Testing email");
            mailSender.send(mailMessage);
            return "Success";
        } catch (Exception e) {
            return "Error";
        }
    }

    // Method to send an email with attachment
    public String sendMailWithAttachment() {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo("taibaalmujaini@gmail.com");
            mimeMessageHelper.setText("Hello Email Body");
            mimeMessageHelper.setSubject("This is the subject");

            // Example of attaching a file from the file system
            FileSystemResource file = new FileSystemResource(new File("Path to local file"));
            mimeMessageHelper.addAttachment(file.getFilename(), file);

            mailSender.send(mimeMessage);
            return "Success";
        } catch (MessagingException e) {
            return "Error";
        }
    }
}
