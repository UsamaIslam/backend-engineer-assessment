FROM gradle:8.4-jdk21 AS builder
# Set the working directory
WORKDIR /app

# Copy the Gradle build files
COPY build.gradle settings.gradle ./

# Copy the source code
COPY src ./src

# Build the application
RUN gradle build --no-daemon

# Use an OpenJDK runtime as a parent image
FROM openjdk:22-ea-21-slim-bullseye

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file from the builder stage
COPY --from=builder /app/build/libs/app-0.0.1-SNAPSHOT.jar /app/app-0.0.1-SNAPSHOT.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the Spring Boot application using Gradle bootRun
CMD ["java", "-jar", "app-0.0.1-SNAPSHOT.jar"]