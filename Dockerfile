# 1️⃣ Build stage
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

# Copy pom.xml and download dependencies (cached layer)
COPY pom.xml .
RUN mvn -B dependency:resolve dependency:resolve-plugins

# Copy source code
COPY src ./src

# Build the jar (skip tests in CI)
RUN mvn -B -DskipTests package

# 2️⃣ Runtime stage (lean)
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy the dynamically detected jar
ARG JAR_FILE
COPY --from=builder /app/target/${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]