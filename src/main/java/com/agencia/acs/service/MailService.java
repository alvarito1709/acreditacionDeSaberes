package com.agencia.acs.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    JavaMailSender mailSender;

    public void recuperarContrasena(String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("registro.acreditacion@bue.edu.ar");

        message.setTo(to);
        message.setSubject(subject);
        message.setText("text");
        mailSender.send(message);
    }

}
