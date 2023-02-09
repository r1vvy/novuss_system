# Use gradle 7.2.0-jdk17 as the base image
FROM gradle:7.2.0-jdk17

# Set the working directory
WORKDIR /app

# Copy the build.gradle file to the working directory
COPY build.gradle /app/build.gradle

# Copy the source code to the working directory
COPY src /app/src

# Build the application
RUN gradle build

# Copy the compiled jar file to the working directory
COPY build/libs/website.jar /app/website.jar

# Set environment variables
ENV PORT 3000

# Define the start command
CMD ["java", "-jar", "website.jar"]

# Expose port 8080
EXPOSE 3000


# TODO: finish this
# tutorial: https://medium.com/geekculture/a-reactjs-web-application-with-a-spring-boot-backend-and-containerizing-it-using-docker-3eeaed8cb45a