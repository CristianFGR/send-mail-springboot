package cl.mailservices.service;

import cl.mailservices.domain.Mail;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import static cl.mailservices.domain.DomainTest.setToMail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by cgonroja on 13-08-19.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class EmailServiceImplTest {

    @InjectMocks
    private EmailServiceImpl emailService;

    @InjectMocks
    private ArgumentCaptor<MimeMessagePreparator> preparatorArgumentCaptor;

    @Mock
    public JavaMailSender emailSender;

    @Spy
    private TemplateEngine templateEngine;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void sendMail() throws Exception {
        Assert.assertEquals(emailService.sendMail(setToMail()), "OK");
    }

    @Test (expected = MailException.class)
    public void sendMailException() throws Exception {
        preparatorArgumentCaptor = ArgumentCaptor.forClass(MimeMessagePreparator.class);
        doThrow(MailSendException.class).when(emailSender).send(preparatorArgumentCaptor.capture());
        emailService.sendMail(setToMail());
    }

    @Test
    public void sendMailMessage() throws Exception {

    }

    @Test
    public void sendMessageWithAttachment() throws Exception {
        Assert.assertEquals(emailService.sendMessageWithAttachment(setToMail(), "test"), "OK");
    }

    @Test (expected = MailException.class)
    public void sendMessageWithAttachmentException() throws Exception {
        preparatorArgumentCaptor = ArgumentCaptor.forClass(MimeMessagePreparator.class);
        doThrow(MailSendException.class).when(emailSender).send(preparatorArgumentCaptor.capture());
        emailService.sendMessageWithAttachment(setToMail(), "test");
    }



}