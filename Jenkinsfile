def project = ‘’
def appName = ‘send-mail’
def imageTag = “gcr.io/${project}/${appName}:${env.BRANCH_NAME}.${env.BUILD_NUMBER}”
pipeline {
 agent {
   node {
     label(‘jenkins-slave-java’)
   }
 }
 stages {
   stage(‘Download Dependency’) {
     steps {
       sh “”"
         mvn clean package
       “”"
     }
   }
   stage(‘SonarQube Analysis’) {
     steps {
      sh “”"
         mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.3.0.603:sonar \
         -Dsonar.host.url=http://35.192.123.229 -Dsonar.login=ecd2422380147418af5c9107b99040804eb5da5b -Dsonar.sources=pom.xml,src/main/,target/classes
       “”"
     }
   }
 }
}