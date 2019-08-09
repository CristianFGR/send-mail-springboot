package cl.mailservices.service;

import cl.mailservices.domain.Mail;
import cl.mailservices.domain.Postgrado;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;

/**
 * Created by cgonroja on 02-07-19.
 */
@Component
public class EmailServiceImpl implements IEmailService {

    private static final Logger LOGGER = LogManager.getLogger(EmailServiceImpl.class);

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void sendMail(Mail mail){
        try{
            MimeMessagePreparator messagePreparator = mimeMessage -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
                messageHelper.setFrom(mail.getFrom());
                messageHelper.setTo(mail.getTo());
                messageHelper.setSubject(mail.getSubject());
                String content = build(setPostgrado());
                messageHelper.setText(content, true);
            };
            emailSender.send(messagePreparator);
        } catch (MailException mailEx) {
            LOGGER.error(mailEx.getMessage());
        }
    }

    @Override
    public void sendMessageWithAttachment(Mail mail, String pathToAttachment) {
        try {
            MimeMessagePreparator messagePreparator = mimeMessage -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
                messageHelper.setFrom(mail.getFrom());
                messageHelper.setTo(mail.getTo());
                messageHelper.setSubject(mail.getSubject());
                String content = build(setPostgrado());
                messageHelper.setText(content, true);
                FileSystemResource file = new FileSystemResource(new File(pathToAttachment+mail.getNameFile()));
                messageHelper.addAttachment(mail.getNameFile(), file);
            };
            emailSender.send(messagePreparator);
        } catch (MailException mailEx) {
            LOGGER.error(mailEx.getMessage());
        }

    }

    private String build(Postgrado postgrado){
        Context context = new Context();
        context.setVariable("nombre", postgrado.getNombre());
        context.setVariable("rut", postgrado.getRut());
        context.setVariable("nacionalidad", postgrado.getNacionalidad());
        context.setVariable("telefonoFijo", postgrado.getTelefonoFijo());
        context.setVariable("telefonoMovil", postgrado.getTelefonoMovil());
        context.setVariable("domicilio", postgrado.getDomicilio());
        context.setVariable("pais", postgrado.getPais());
        context.setVariable("tituloProfesional", postgrado.getTituloProfesional());
        context.setVariable("casaEstudios", postgrado.getCasaEstudios());
        context.setVariable("ubicacionCasaEstudios", postgrado.getUbicacionCasaEstudios());
        context.setVariable("anioTitulacion", postgrado.getAnioTitulacion());
        context.setVariable("ocupacion", postgrado.getOcupacion());
        context.setVariable("programa", postgrado.getPrograma());
        context.setVariable("financiamiento", postgrado.getFinanciamiento());
        context.setVariable("comentario", postgrado.getComentario());
        context.setVariable("adjunto", postgrado.getAdjunto());
        return templateEngine.process("email", context);
    }

    private Postgrado setPostgrado(){
        Postgrado postgrado = new Postgrado();
        postgrado.setNombre("Julio Cornejo Test");
        postgrado.setRut("15.246.675-8");
        postgrado.setNacionalidad("De Caragüe");
        postgrado.setTelefonoFijo("555555555");
        postgrado.setTelefonoMovil("56962221343");
        postgrado.setDomicilio("calle siempre viva 123");
        postgrado.setPais("Santiago / Chile");
        postgrado.setTituloProfesional("Prestamista");
        postgrado.setCasaEstudios("MIT");
        postgrado.setUbicacionCasaEstudios("USA");
        postgrado.setAnioTitulacion("2007");
        postgrado.setOcupacion("CEO Ragnax");
        postgrado.setPrograma("Eliminar Gente por Dinero");
        postgrado.setFinanciamiento("Prestamos a terceros");
        postgrado.setComentario("Migrar todos los aplicativos a microservicios");
        postgrado.setAdjunto("aca debiese ir un link");
        return postgrado;
    }

}
