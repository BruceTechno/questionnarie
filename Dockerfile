FROM openjdk:11-jdk
ARG JAR_FILE=build/*.jar
COPY ./build/questionnaire-0.0.1-SNAPSHOT.jar ./
ENTRYPOINT ["java","-jar","/app.jar"]