FROM eclipse-temurin:17.0.11_9-jre
COPY big-event-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
