FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/JavaAndersen-1.0-SNAPSHOT.jar /app/JavaAndersen.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "JavaAndersen.jar"]