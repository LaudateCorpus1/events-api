FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/events-0.0.1.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]