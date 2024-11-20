FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy Maven files first for better caching
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Make mvnw executable
RUN chmod +x mvnw

# Build the application
COPY src src
RUN ./mvnw clean package -DskipTests

# Set the jar as the entrypoint
ENTRYPOINT ["java", "-jar", "target/waitingList-0.0.1-SNAPSHOT.jar"]