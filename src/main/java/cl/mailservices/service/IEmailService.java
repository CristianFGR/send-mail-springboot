package cl.mailservices.service;

import cl.mailservices.domain.Mail;

/**
 * Created by cgonroja on 02-07-19.
 */
public interface IEmailService {

    String sendMail(Mail mail);

    String sendMessageWithAttachment(Mail mail, String nameFile);
}
