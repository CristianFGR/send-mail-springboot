package cl.mailservices.controller;

import cl.mailservices.domain.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by cgonroja on 09-08-19.
 */
@ControllerAdvice
@ResponseBody
public class ExceptionControllerAdvice {

    private static final Logger LOGGER = LogManager.getLogger(ExceptionControllerAdvice.class);

    /**
     * Metodo que captura la excepcion generada al no poder ser enviado el correo.
     *
     * @param mailEx excepcion del tipo MailException.
     * @return ResponseEntity<Response> con el error capturado y el codigo HTTP
     */
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(MailException.class)
    public ResponseEntity<Response> handlerException(MailException mailEx) {
        LOGGER.error("Error al generar el envio de correo {} .", mailEx.getMessage());
        return new ResponseEntity<>(new Response(mailEx.getMessage(), HttpStatus.SERVICE_UNAVAILABLE.value()),
                HttpStatus.SERVICE_UNAVAILABLE);
    }
}
