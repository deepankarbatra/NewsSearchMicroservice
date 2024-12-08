FROM openjdk:17-jdk-slim


WORKDIR /app


COPY target/NewsSearch-0.0.1-SNAPSHOT.jar /app/NewsSearch-0.0.1-SNAPSHOT.jar


COPY NewsSearchFrontend/dist /app/NewsSearchFrontend


EXPOSE 8080


ENTRYPOINT ["java", "-jar", "/app/NewsSearch-0.0.1-SNAPSHOT.jar"]