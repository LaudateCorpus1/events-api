FROM adoptopenjdk/openjdk11:alpine-jre
COPY target/events-0.0.1.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]