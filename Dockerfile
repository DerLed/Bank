FROM openjdk:11
MAINTAINER Dmitriy Lebedev
ADD /target/Bank-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080