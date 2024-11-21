FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "/app/target/citronix-0.0.1-SNAPSHOT.jar"]