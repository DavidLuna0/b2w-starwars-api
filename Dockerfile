FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
EXPOSE 8081
COPY ${JAR_FILE} starwars.jar
CMD ["java", "-Dspring.data.mongodb.uri=mongodb://starwars-mongo:27017/starwars-api","-Djava.security.egd=file:/dev/./urandom","-jar","./starwars.jar"]