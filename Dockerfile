FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
EXPOSE 8081
COPY ${JAR_FILE} starwars.jar
CMD ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","./starwars.jar"]