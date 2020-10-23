FROM openjdk:8-jdk-alpine
EXPOSE 8081
RUN mkdir -p /app/
ADD target/starwars-api-0.2.0.jar /app/starwars-api-0.2.0.jar
ENTRYPOINT ["java", "-jar", "/app/starwars-api-0.2.0.jar"]