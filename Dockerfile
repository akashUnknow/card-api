# =========================
# 1. Build Stage
# =========================
FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code and build JAR
COPY src ./src
RUN mvn clean package -DskipTests

# =========================
# 2. Run Stage
# =========================
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy built jar from build stage and rename to app.jar
COPY --from=build /app/target/cardInventory-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8080

# Run app
ENTRYPOINT ["java", "-jar", "app.jar"]
