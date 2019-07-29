package cl.mailservices.controller;

import cl.mailservices.domain.Mail;
import cl.mailservices.service.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by cgonroja on 02-07-19.
 */
@Controller
@RequestMapping("/mail")
public class MailController {

    @Autowired
    public EmailServiceImpl emailService;

    @Autowired
    public SimpleMailMessage template;

    @Value("${attachment.invoice}")
    private String attachmentPath;

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public ResponseEntity<String> createMail(@RequestBody @Valid Mail mailObject, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>("OK", HttpStatus.SERVICE_UNAVAILABLE);
        }
        emailService.sendSimpleMessage(mailObject.getTo(), mailObject.getSubject(), mailObject.getText());

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @RequestMapping(value = "/sendTemplate", method = RequestMethod.POST)
    public ResponseEntity<String> createMailWithTemplate(@RequestBody @Valid Mail mailObject, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>("OK", HttpStatus.SERVICE_UNAVAILABLE);
        }
        emailService.sendSimpleMessageUsingTemplate(mailObject.getTo(), mailObject.getSubject(), template,
                mailObject.getText());

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @RequestMapping(value = "/sendAttachment", method = RequestMethod.POST)
    public ResponseEntity<String> createMailWithAttachment(@RequestBody @Valid Mail mailObject, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>("OK", HttpStatus.SERVICE_UNAVAILABLE);
        }
        emailService.sendMessageWithAttachment(mailObject.getTo(), mailObject.getSubject(), mailObject.getText(),
                attachmentPath
        );

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
