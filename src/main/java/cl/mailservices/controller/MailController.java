package cl.mailservices.controller;

import cl.mailservices.domain.Mail;
import cl.mailservices.service.IEmailService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Created by cgonroja on 02-07-19.
 */
@Controller
@RequestMapping("/mail")
public class MailController {

    @Autowired
    public IEmailService emailService;

    @Value("${attachment.filePath}")
    private String attachmentPath;

    @ApiResponses(value = {
            @ApiResponse(code = 503, message = "Error con el servicio envio de Correo", response = String.class)})
    @PostMapping(value = "/send")
    public ResponseEntity<String> createMail(@RequestBody @Valid Mail mailObject, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(errors.getAllErrors().toString(), HttpStatus.SERVICE_UNAVAILABLE);
        }
        emailService.sendMail(mailObject);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping(value = "/send-with-attachment")
    public ResponseEntity<String> createMailWithAttachment(@RequestBody @Valid Mail mailObject, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(errors.getAllErrors().toString(), HttpStatus.SERVICE_UNAVAILABLE);
        }
        emailService.sendMessageWithAttachment(mailObject, attachmentPath);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
