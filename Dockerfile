#
# Build stage
#
FROM maven:3.8.2-jdk-11 AS build
COPY . .
RUN mvn clean

#
# Package stage
#
FROM openjdk:11-jdk-slim
COPY mvn -pl backend spring-boot:run -Dspring-boot.run.profiles=dev,seeder
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]
