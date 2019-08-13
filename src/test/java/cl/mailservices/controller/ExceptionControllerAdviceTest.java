package cl.mailservices.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;

/**
 * Created by cgonroja on 13-08-19.
 */
public class ExceptionControllerAdviceTest {

    @InjectMocks
    private ExceptionControllerAdvice controllerAdvice;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void handlerException() throws Exception {
        MailException mailException = new MailSendException("ERROR EN EL ENVIO DE CORREO");
        Assert.assertEquals(controllerAdvice.handlerException(mailException).getStatusCodeValue(), 503);
    }

}