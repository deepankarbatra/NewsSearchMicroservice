FROM openjdk:17-jdk-slim


WORKDIR /app


COPY NewsSearchBackend/target/NewsSearch-0.0.1-SNAPSHOT.jar /app/NewsSearch-0.0.1-SNAPSHOT.jar


EXPOSE 8080


ENTRYPOINT ["java", "-jar", "/app/NewsSearch-0.0.1-SNAPSHOT.jar"]