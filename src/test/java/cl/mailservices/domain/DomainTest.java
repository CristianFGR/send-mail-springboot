package cl.mailservices.domain;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by cgonroja on 13-08-19.
 */
public class DomainTest {

    @Test
    public void setMail() throws Exception {
        Mail mail = setToMail();
        Assert.assertEquals(mail.getFrom(), "prueba@mail.cl");
        Assert.assertEquals(mail.getNameFile(), "nameFile");
        Assert.assertEquals(mail.getTo(), "mail@mail.cl");
        Assert.assertEquals(mail.getSubject(), "Test");
        Assert.assertEquals(mail.getTemplateCorreo(), "template");
    }

    @Test
    public void setPosgrado() throws Exception {
        Postgrado postgrado = setToPostgrado();
        Assert.assertEquals(postgrado.getNombre(), "TEST");
        Assert.assertEquals(postgrado.getRut(), "TEST");
        Assert.assertEquals(postgrado.getNacionalidad(), "TEST");
        Assert.assertEquals(postgrado.getTelefonoFijo(), "TEST");
        Assert.assertEquals(postgrado.getTelefonoMovil(), "TEST");
        Assert.assertEquals(postgrado.getDomicilio(), "TEST");
        Assert.assertEquals(postgrado.getPais(), "TEST");
        Assert.assertEquals(postgrado.getTituloProfesional(), "TEST");
        Assert.assertEquals(postgrado.getCasaEstudios(), "TEST");
        Assert.assertEquals(postgrado.getUbicacionCasaEstudios(), "TEST");
        Assert.assertEquals(postgrado.getAnioTitulacion(), "TEST");
        Assert.assertEquals(postgrado.getOcupacion(), "TEST");
        Assert.assertEquals(postgrado.getPrograma(), "TEST");
        Assert.assertEquals(postgrado.getFinanciamiento(), "TEST");
        Assert.assertEquals(postgrado.getComentario(), "TEST");
        Assert.assertEquals(postgrado.getAdjunto(), "TEST");
    }

    @Test
    public void setResponse() throws Exception {
        Response response = new Response("TEST", 200);
        Assert.assertEquals(response.getMensaje(), "TEST");
        Assert.assertEquals(response.getCodigoHttp(), 200);
        response.setMensaje("TEST2");
        response.setCodigoHttp(400);
        Assert.assertEquals(response.getMensaje(), "TEST2");
        Assert.assertEquals(response.getCodigoHttp(), 400);
    }

    public static Mail setToMail(){
        Mail mail = new Mail();
        mail.setFrom("prueba@mail.cl");
        mail.setNameFile("nameFile");
        mail.setTo("mail@mail.cl");
        mail.setSubject("Test");
        mail.setTemplateCorreo("template");
        return mail;
    }

    public static Postgrado setToPostgrado() {
        Postgrado postgrado = new Postgrado();
        postgrado.setNombre("TEST");
        postgrado.setRut("TEST");
        postgrado.setNacionalidad("TEST");
        postgrado.setTelefonoFijo("TEST");
        postgrado.setTelefonoMovil("TEST");
        postgrado.setDomicilio("TEST");
        postgrado.setPais("TEST");
        postgrado.setTituloProfesional("TEST");
        postgrado.setCasaEstudios("TEST");
        postgrado.setUbicacionCasaEstudios("TEST");
        postgrado.setAnioTitulacion("TEST");
        postgrado.setOcupacion("TEST");
        postgrado.setPrograma("TEST");
        postgrado.setFinanciamiento("TEST");
        postgrado.setComentario("TEST");
        postgrado.setAdjunto("TEST");
        return postgrado;
    }


}