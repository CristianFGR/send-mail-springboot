package cl.mailservices.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by cgonroja on 02-07-19.
 */
public class Mail {

    @Email
    @NotNull
    @Size(min = 1, message = "Please, set an email address to send the message to it")
    private String to;
    private String from;
    private String subject;

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

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }
}
