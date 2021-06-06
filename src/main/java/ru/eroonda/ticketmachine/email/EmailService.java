package ru.eroonda.ticketmachine.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class EmailService implements EmailSender {

    @Autowired
    private JavaMailSender mailSender;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Override
    @Async
    public void send(String to, String emailSubject, String emailMessage) {

        try {
            LOGGER.info("start to send a mail to" + to + " subject: " + emailSubject);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("ticketmachine8@gmail.com");
            message.setTo(to);
            message.setSubject(emailSubject);
            message.setText(emailMessage);
            mailSender.send(message);
            LOGGER.info("Email was successfully send to" + to + " subject: " + emailSubject);

        } catch (MailException e){
            LOGGER.error("failed to send email to: " + to + " subject:" + emailSubject, e );
            throw new IllegalStateException("failed to send email to: " + to + " subject:" + emailSubject);
        }
    }
}
