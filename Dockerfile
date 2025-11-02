# Multi-stage Dockerfile for building and running the Spring Boot app
# Builder stage: uses Maven + JDK to build the fat jar
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /workspace

# Use the maven wrapper if present for reproducible builds
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN chmod +x mvnw || true

# Copy source and build
COPY src ./src
RUN ./mvnw -B -DskipTests package

# Runtime stage: lightweight JRE
FROM eclipse-temurin:17-jre AS runtime
WORKDIR /app

# Adjust this JAR name if your artifactId/version changes
COPY --from=builder /workspace/target/expense-0.0.1-SNAPSHOT.jar ./app.jar

# Expose the port set in application.properties
EXPOSE 8082

ENTRYPOINT ["java", "-jar", "/app/app.jar"]

