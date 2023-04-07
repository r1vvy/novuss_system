FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY auth-service-app/build/libs/auth-service-app-1.0-SNAPSHOT-all.jar auth-service-app.jar

EXPOSE ${AUTH_SERVICE_PORT}

ENTRYPOINT ["java","-jar", "/app/auth-service-app.jar"]
