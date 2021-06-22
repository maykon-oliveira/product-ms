FROM openjdk:11.0-jre-slim

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 9999

ENTRYPOINT ["java","-jar","/app.jar"]
