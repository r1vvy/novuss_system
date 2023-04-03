FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY build/libs/auth-service-1.0-SNAPSHOT.jar /app/auth-service-app.jar

EXPOSE ${AUTH_SERVICE_PORT}

ENTRYPOINT ["java", "-cp", "/app/auth-service-app.jar", "com.novuss.authservice.app.AuthorizationApp"]
