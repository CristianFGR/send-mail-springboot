restful-springboot-mail
=========

Fuentes del arquetipo RestFul SpringBoot backend para envio de correo.
Para compilar
```
mvn clean install
```
Para ejecutar
```
mvn spring-boot:run
```

##### USO

Para utilizar este aplicativo para envio de correo tradicional
```
POST ->  http://localhost:8080/mail/send

{
    "to": "cristianfgr@ragnaxsistemas.cl",
    "subject": "Pruebas de correo",
     "from": "test@mail.cl"
}
```
Envio de correo con archivo adjunto
```
POST -> http://localhost:8080/mail//send-with-attachment

{
    "to": "cristianfgr@ragnaxsistemas.cl",
    "subject": "Pruebas de correo",
     "from": "test@mail.cl",
     "nameFile":"declaComp.pdf"
}
```

 