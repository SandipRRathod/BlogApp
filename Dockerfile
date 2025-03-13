FROM openjdk:19

EXPOSE 3607

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file to the container
COPY target/blogapp.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
