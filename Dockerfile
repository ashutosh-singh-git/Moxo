FROM maven:3.8.1-openjdk-16-slim

WORKDIR /opt
ENV PORT 8080
EXPOSE 8080

ENV mongo_auth_db bike
ENV mongo_user local
ENV mongo_pass local
ENV mongo_db bike
ENV mongo_port 27017
ENV mongo_host host.docker.internal

COPY src /opt/src
COPY pom.xml /opt
RUN mvn -f /opt/pom.xml clean install

COPY target/*.jar /opt/app.jar
ENTRYPOINT exec java $JAVA_OPTS --enable-preview -jar app.jar
