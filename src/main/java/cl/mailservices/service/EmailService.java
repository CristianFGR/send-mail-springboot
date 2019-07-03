package cl.mailservices.service;

import org.springframework.mail.SimpleMailMessage;

/**
 * Created by cgonroja on 02-07-19.
 */
public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);

    void sendSimpleMessageUsingTemplate(String to, String subject, SimpleMailMessage template, String ...templateArgs);

    void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment);
}
