FROM openjdk:8-jdk-alpine
# Add a volume pointing to /tmp
VOLUME /tmp
# Make port 8080 available to the world outside this container
EXPOSE 8080
# The application’s jar file
ARG JAR_FILE=target/send-mail-1.0.0.jar
# Add the application’s jar to the container
ADD ${JAR_FILE} send-mail.jar
# Run the jar file
ENTRYPOINT [“java”,“-jar”,“/send-mail.jar”]