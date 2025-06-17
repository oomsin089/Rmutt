FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy gradle wrapper และให้สิทธิ์ execute
COPY gradlew ./
COPY gradle ./gradle
RUN chmod +x ./gradlew

# Copy build files
COPY build.gradle.kts settings.gradle.kts ./

# Download dependencies (optional - for faster builds)
RUN ./gradlew dependencies --no-daemon

# Copy source code
COPY src ./src

# Build the application
RUN ./gradlew build -x test --no-daemon

# Find the built jar file
RUN find build/libs -name "*.jar" -not -name "*plain*" -exec cp {} app.jar \;

# Expose port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]