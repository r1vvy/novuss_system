FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY . .
RUN ./gradlew build
EXPOSE 8080
CMD ["java", "-jar", "build/libs/restful-service.jar"]
