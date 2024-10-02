package com.agencia.acs.service;


import com.agencia.acs.entities.Token;
import com.agencia.acs.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Service
public class MailService {

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private HttpSession session;

    @Autowired
    private ServletContext servletContext;

    public void recuperarContrasena(String to, String subject, String
            templateName, User user, String tokenUrl) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);

            String template = processTemplate(user, tokenUrl);

            //WebContext webContext = new WebContext( request, response,servletContext);
            //String html = templateEngine.process(template, webContext);

            helper.setText(template, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            // Manejar excepciones
        }
    }

    public String processTemplate(User user, String token) {
        Context context = new Context();
        context.setVariable("usuario", user.getNombre());
        context.setVariable("token", token);
        return templateEngine.process("mail", context);

    }

}
