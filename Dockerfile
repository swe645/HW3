# Use a base image with Java 18
FROM maven:3.8.5-openjdk-18 AS build

# Set the working directory
WORKDIR /app

# Copy the Maven project file
COPY pom.xml .

# Download dependencies
RUN mvn -B dependency:go-offline

# Copy the application source code
COPY src ./src

# Build the application
RUN mvn -B package

# Create a new image with the JAR file
FROM openjdk:18-jdk-alpine3.14

# Set the working directory
WORKDIR /app

# Copy the JAR file from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
