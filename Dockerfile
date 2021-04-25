FROM adoptopenjdk/openjdk16:alpine
WORKDIR /opt
ENV PORT 8080
EXPOSE 8080

ENV mongo_auth_db bike
ENV mongo_user local
ENV mongo_pass local
ENV mongo_db bike
ENV mongo_port 27017
ENV mongo_host host.docker.internal

COPY target/*.jar /opt/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar
