# Stage 1: Build and Test
FROM maven:3.9-eclipse-temurin-11 AS builder

WORKDIR /app

# Copy project files
COPY pom.xml .
COPY *.java .
COPY test/ test/

# Download dependencies
RUN mvn dependency:go-offline

# Run tests
RUN mvn test

# Compile the application
RUN mvn compile

# Stage 2: Runtime
FROM eclipse-temurin:11-jre

WORKDIR /app

# Copy compiled classes from builder stage
COPY --from=builder /app/target/classes /app/classes
COPY --from=builder /app/*.java /app/

# Set the classpath
ENV CLASSPATH=/app/classes:/app

# Default command to run the game
CMD ["java", "Main"]
