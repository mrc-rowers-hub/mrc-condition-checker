# Build stage
FROM maven:3.9.8-eclipse-temurin-21 AS build

WORKDIR /home/maven/src

# Copy your Maven configuration and source code
COPY pom.xml .
COPY src ./src

# Build the application (skip tests for faster builds)
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jre

# Expose the correct port for your app (adjust if necessary)
EXPOSE 8080

# Copy the JAR file built in the previous stage to the container's /app directory
COPY --from=build /home/maven/src/target/condition_checker-0.0.1-SNAPSHOT.jar /app/condition_checker.jar

# Run the Java application
CMD ["java", "-jar", "/app/condition_checker.jar"]
