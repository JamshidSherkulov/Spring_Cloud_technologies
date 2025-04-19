# Base image
FROM openjdk:17-jdk-slim

# App directory inside the container
WORKDIR /app

# Copy the built jar file
COPY target/*.jar app.jar

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
