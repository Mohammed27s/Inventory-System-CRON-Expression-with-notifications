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

//This is a mailingService service
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    String sender;

    public String sendSimpleMail(String toEmail, String fromEmail, String emailBody, String subject){

        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(fromEmail);
            mailMessage.setTo(toEmail);
            mailMessage.setText(emailBody);
            mailMessage.setSubject(subject);
            mailSender.send(mailMessage);
            return "The email has been send successfully ";
        } catch (Exception e){

            return "Failed to send the Email";
        }

    }


    public String sendMailWithAttachment(){

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;


        try{

            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo("mohd.com25@gmail.com");
            mimeMessageHelper.setText("Dear Hiring Manager" +
                    ", I want vacation before Eid for one week, " +
                    "sincerely, " +
                    "Mohammed Salim");
            mimeMessageHelper.setSubject("Eid Vacation");
            FileSystemResource file = new FileSystemResource(new File("Path to local file"));
            mimeMessageHelper.addAttachment(file.getFilename(), file);
            mailSender.send(mimeMessage);
            return "Your email has been sent successfully";

        } catch (MessagingException e){
            return "Error";
        }


    }

}
