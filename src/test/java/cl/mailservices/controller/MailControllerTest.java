package cl.mailservices.controller;

import cl.mailservices.domain.Mail;
import cl.mailservices.service.IEmailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;

import java.nio.file.Files;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by cgonroja on 12-08-19.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MailControllerTest {

    MockMvc mockMvc;

    @InjectMocks
    private MailController controller;

    @MockBean
    private IEmailService emailService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(this.controller)
                .setControllerAdvice(new ExceptionControllerAdvice())
                .build();
    }

    @Test
    public void createMail() throws Exception {
        when(emailService.sendMail(Mockito.any(Mail.class))).thenReturn("OK");
        byte[] contentRequest = Files.readAllBytes(ResourceUtils.getFile("classpath:json/rq.json").toPath());
        mockMvc.perform(post("/mail/send").contentType(MediaType.APPLICATION_JSON)
                .content(contentRequest))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void createMailErrorEx() throws Exception {
        byte[] contentRequest = Files.readAllBytes(ResourceUtils.getFile("classpath:json/rqErrorData.json").toPath());
        mockMvc.perform(post("/mail/send").contentType(MediaType.APPLICATION_JSON)
                .content(contentRequest))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createMailWithAttachment() throws Exception {
        when(emailService.sendMessageWithAttachment(Mockito.any(Mail.class), anyString())).thenReturn("OK");
        byte[] contentRequest = Files.readAllBytes(ResourceUtils.getFile("classpath:json/rq.json").toPath());
        mockMvc.perform(post("/mail/send-with-attachment").contentType(MediaType.APPLICATION_JSON)
                .content(contentRequest))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void createMailWithAttachmentErrorEx() throws Exception {
        byte[] contentRequest = Files.readAllBytes(ResourceUtils.getFile("classpath:json/rqErrorData.json").toPath());
        mockMvc.perform(post("/mail/send-with-attachment").contentType(MediaType.APPLICATION_JSON)
                .content(contentRequest))
                .andExpect(status().is4xxClientError());
    }

}