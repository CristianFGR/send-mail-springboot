def project = ''
def appName = 'send-mail'
pipeline {
 agent {
   node {
     label('slave-java-01')
   }
 }
 stages {
   stage('Download Dependency') {
     steps {
       sh """
         /opt/apache-maven-3.6.1/bin/mvn clean package
       """
     }
   }
   stage('SonarQube Analysis') {
     steps {
      sh """
         /opt/apache-maven-3.6.1/bin/mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.3.0.603:sonar \
         -Dsonar.host.url=http://35.192.123.229 -Dsonar.login=ecd2422380147418af5c9107b99040804eb5da5b -Dsonar.sources=pom.xml,src/main/,target/classes
       """
     }
   }
 }
}