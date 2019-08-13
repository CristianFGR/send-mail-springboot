package cl.mailservices.controller;

import cl.mailservices.domain.Mail;
import cl.mailservices.domain.Response;
import cl.mailservices.service.IEmailService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

/**
 * Created by cgonroja on 02-07-19.
 */
@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private IEmailService emailService;

    @Value("${attachment.filePath}")
    private String attachmentPath;

    /**
     * Metodo que envia correo con template
     * @param mailObject objeto que posee los datos necesarios para enviar el correo.
     * @param errors error en la validacion de los datos de entrada, direccion de correo, a quien va dirigido
     *              y el asunto.
     * @return ResponseEntity<Response> con codigo 200 en caso de exito.
     */
    @ApiOperation(value = "Envio de correo con template", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 422, message = "Error al procesar los datos de entrada del correo",
                    response = Response.class),
            @ApiResponse(code = 503, message = "Error con el servicio envio de Correo", response = Response.class),
            @ApiResponse(code = 200, message = "Correo enviado satisfactoriamente", response = Response.class)
    })
    @PostMapping(value = "/send")
    public ResponseEntity<Response> createMail(@ApiParam(value = "objeto de entrada", required = true)
                                                @RequestBody @Valid Mail mailObject, @ApiIgnore Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(new Response(errors.getAllErrors().toString(),
                    HttpStatus.UNPROCESSABLE_ENTITY.value()), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(new Response(emailService.sendMail(mailObject), HttpStatus.OK.value()),
                                    HttpStatus.OK);
    }

    /**
     * Metodo que envia correo con template y archivos adjunto
     * @param mailObject objeto que posee los datos necesarios para enviar el correo
     * @param errors error en la validacion de los datos de entrada, direccion de correo, a quien va dirigido
     *              y el asunto
     * @return ResponseEntity<Response> con codigo 200 en caso de exito.
     */
    @ApiOperation(value = "Envio de correo con template y archivo(s) adjunto(s)", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 422, message = "Error al procesar los datos de entrada del correo",
                    response = Response.class),
            @ApiResponse(code = 503, message = "Error con el servicio envio de Correo", response = Response.class),
            @ApiResponse(code = 200, message = "Correo enviado satisfactoriamente", response = Response.class)
    })
    @PostMapping(value = "/send-with-attachment")
    public ResponseEntity<Response> createMailWithAttachment(@ApiParam(value = "objeto de entrada", required = true)
                                                             @RequestBody @Valid Mail mailObject,
                                                             @ApiIgnore Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(new Response(errors.getAllErrors().toString(),
                    HttpStatus.UNPROCESSABLE_ENTITY.value()), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(new Response(emailService.sendMessageWithAttachment(mailObject, attachmentPath),
                                    HttpStatus.OK.value()), HttpStatus.OK);
    }

}
