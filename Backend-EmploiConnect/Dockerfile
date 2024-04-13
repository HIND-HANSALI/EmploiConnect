# Build Stage
FROM  maven:3.9.5-eclipse-temurin-17-alpine AS build


# Set the working directory inside the container
WORKDIR /app

# Copy the project's POM file to the working directory
#COPY pom.xml ./pom.xml

# Copy the entire source code to the working directory
#COPY src ./src

# Run Maven commands to build the application
#RUN mvn clean package -DskipTests

# Copy the compiled JAR file from the build stage to the working directory
#COPY --from=build /app/target/*.jar ./app.jar

#COPY ./target/EmploiConnect-0.0.1-SNAPSHOT.jar /app.jar

COPY target/*.jar app.jar

EXPOSE 8080

# Specify the command to run when the container starts
CMD ["java", "-jar", "app.jar"]

