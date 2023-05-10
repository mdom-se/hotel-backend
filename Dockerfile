FROM openjdk:8u342-jre
COPY target/hotel-backend-0.0.1-SNAPSHOT.jar hotel-backend.jar
ENTRYPOINT ["java", "-jar", "hotel-backend.jar"]