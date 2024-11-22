FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-21-jdk -y

WORKDIR /app
COPY . .
RUN ./gradlew bootJar --no-daemon

FROM openjdk:21-jdk-slim
WORKDIR /app
EXPOSE 8080
COPY --from=build /app/build/libs/BackIBancaria-0.0.1.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
