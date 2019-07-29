arquetipo-restfull-springboot-mail
=========

Fuentes del arquetipo RestFull SpringBoot backend para envio de correo.
Para compilar
```
mvn clean install
```
Para ejecutar
```
mvn spring-boot:run
```

##### USO

Para utilizar este aplicativo, se necesita un json en el body con la siguiente
estructura
```
{
      "to": "correo destinatario",
      "subject": "asunto para el correo",
      "text": "lo que se quiere informar"
}
```

Envio de correo tradicional
```
http://localhost:8080/mail/send
```
Envio de correo con templete
```
http://localhost:8080/mail/sendTemplate
```
Envio de correo con archivo adjunto
```
http://localhost:8080/mail/sendAttachment

 