# Stage 1: Build stage
FROM maven:3.8.4-openjdk-17-slim AS build

# Copy Maven files for dependency resolution
COPY pom.xml ./
COPY .mvn .mvn

# Copy application source code
COPY src src

# Build the project and create the executable JAR
RUN mvn clean install -DskipTests

# Stage 2: Run stage
FROM openjdk:17-jdk-slim

# Set working customerauth
WORKDIR customer-account-manager

# Copy the JAR file from the build stage
COPY --from=build target/*.jar customerauht.jar

# Expose port 1222
EXPOSE 1222

# Set the entrypoint command for running the application
ENTRYPOINT ["java", "-jar", "customerauht.jar"]