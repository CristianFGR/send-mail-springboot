package cl.mailservices.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by cgonroja on 02-07-19.
 */
@ApiModel
public class Mail implements Serializable {

    private static final long serialVersionUID = 2L;

    @Email
    @NotNull
    @Size(min = 1, message = "Please, set an email address to send the message to it")
    @ApiModelProperty(notes = "direccion de correo a la que va dirigido", required = true)
    private String to;
    @ApiModelProperty(notes = "direccion de correo desde donde va dirigido", required = true)
    private String from;
    @ApiModelProperty(notes = "Asunto del correo", required = true)
    private String subject;

    @NotNull
    @ApiModelProperty(notes = "template del correo que se utilizara", required = true)
    private String templateCorreo;

    @ApiModelProperty(notes = "nombre del archivo que se adjuntara")
    private String nameFile;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTemplateCorreo() {
        return templateCorreo;
    }

    public void setTemplateCorreo(String templateCorreo) {
        this.templateCorreo = templateCorreo;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }
}
