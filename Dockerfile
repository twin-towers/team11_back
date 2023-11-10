FROM openjdk:17
ARG jarFile=/target/hackathon11-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${jarFile} hackathon.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","hackathon.jar"]