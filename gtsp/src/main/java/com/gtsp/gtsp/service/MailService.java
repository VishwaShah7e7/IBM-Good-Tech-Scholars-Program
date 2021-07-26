package com.gtsp.gtsp.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.gtsp.gtsp.exception.GtspException;
import com.gtsp.gtsp.model.NotificationEmail;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {
	private final JavaMailSender mailSender;
    private final MailContentBuilder mailContentBuilder;
    //Logger logger = LoggerFactory.getLogger(MailService.class);

    @Async
    void sendMail(NotificationEmail notificationEmail) {
    	// Recipient's email ID needs to be mentioned.
        String to = notificationEmail.getRecipient();

        // Sender's email ID needs to be mentioned
        String from = "testing.7.6.2021@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("testing.7.6.2021@gmail.com", "Immiguide@4");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(notificationEmail.getSubject());

            // Now set the actual message
            message.setText(notificationEmail.getBody());
            //logger.debug("sending...");

           
            // Send message
            Transport.send(message);
            //logger.debug("Sent message successfully...");
            
        } catch (MessagingException e) {
        	log.error("Exception occurred when sending mail", e);
            throw new GtspException("Exception occurred when sending mail to " + notificationEmail.getRecipient(), e);
        }
       /* MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("testing.7.6.2021@gmail.com");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
        };
        try {
            mailSender.send(messagePreparator);
            log.info("Activation email sent!!");
        } catch (MailException e) {
            log.error("Exception occurred when sending mail", e);
            throw new GtspException("Exception occurred when sending mail to " + notificationEmail.getRecipient(), e);
        }*/
    }
}
