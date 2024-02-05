FROM tomcat:9.0.85-jdk11-temurin-focal
COPY /build/libs/HPass-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080