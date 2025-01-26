FROM openjdk:21-jdk-slim
COPY build/libs/user-0.0.1-SNAPSHOT.jar userApp.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "userApp.jar"]

